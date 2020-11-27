# mybatisRedisCache

创建了两种 redis缓存做法

一种config目录，基于在方法上添加注解实现。  
  优点，可以自定义配置key，value等，更加灵活。  
  缺点，在方法上添加注解，需要配置的地方就很多，代码量增加

    springboot 启动类上加上注解 @EnableCaching，开启缓存
    Cacheable 注意保存到redis中的对象是同一个对象，否则多个地方读取出来会序列化失败
    注解：
    注解中的key是根据入参时获取的内部值，
    所以insert无法获取student中的id
    
    @Cacheable(value = "user", key = "#student.id")
    将结果缓存进 user 中，key 为 student的id属性，每次会先查询Redis是否存在
    
    @CachePut(value = "user", key = "#student.id")
    更新结果，不管Redis中是否存在，都会先查询数据库，再将结果更新进Redis
    
    @CacheEvict(value = "user", key = "#id")
    根据value和key对缓存删除

一种在 cache目录，在 Mapper中添加开启二级缓存。  
  优点，mybatis框架支持，简单，代码量少。  
  缺点是不够灵活，测试中发现，当delete执行后，会将reids中该mapper缓存的数据都删除掉，主要原因是因为二级缓存flush整个DB，导致不需要失效的缓存也给失效掉了。  

