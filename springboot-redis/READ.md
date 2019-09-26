
最近项目中需要埋点计数。

使用redis做存储，因为累加计数，所以使用了
```
// 自增1
redisTemplate.boundValueOps(key).increment(1);

// 自增data
redisTemplate.opsForValue().increment(key, data);
```

但是，在执行时，提示
```
nested exception is io.lettuce.core.RedisCommandExecutxception: ERR value is not an integer or out of range
```
发现由于存储到redis时进行序列化，默认序列化采用其JdkSerializationRedisSerializer。

默认的方案 JdkSerializationRedisSerializer 使用的 jdk 对象序列化，序列化后的值有类信息、版本号等，所以是一个包含很多字母的字符串，所以根本无法加1。
GenericJackson2JsonRedisSerializer、Jackson2JsonRedisSerializer是先将对象转为json，然后再保存到redis，所以，1在redis中是字符串1，所以无法进行加1。
而 GenericToStringSerializer、StringRedisSerializer将字符串的值直接转为字节数组，所以保存到redis中是数字，所以可以进行加1。

于是方法改成：

```
ValueOperations<String, String> operations = redisTemplate.opsForValue();
// 自定义序列化类型
// redisTemplate.setKeySerializer(new StringRedisSerializer());
redisTemplate.setValueSerializer(new StringRedisSerializer());
// 对应的key自增1，没有该key，会自动创建
operations.increment(key, 1);
```

但是这又带来了新的问题，因为存储时是根据 StringRedisSerializer 进行序列化的，读取时也需要根据特定的序列化方式才能够读取。

于是只能在读取计数时采用单独的读取方案才能够读取。

```
public String getIncr(String key) {
   ValueOperations<String, String> operations = redisTemplate.opsForValue();
   redisTemplate.setValueSerializer(new StringRedisSerializer());
   return operations.get(key);
}
```


其实没必要更改 key 的序列化方式。

但测试发现，com.redis.util.RedisUtil 中increment()使用普通的自增方法，然后getIncr()依旧可以取到值。
在redis中查看也是正常的。原因目前不是很清楚。