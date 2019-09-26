package com.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author: meimengling
 * @Date: 2019/9/25 15:49
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public Long increment(final String key, Long data) {
//        return redisTemplate.opsForValue().increment(key, data);
        return redisTemplate.boundValueOps(key).increment(data);
    }

    public String getIncr(String key) {
        // 设置系列化类型
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return operations.get(key);
    }

    /*public <T> T get(String key, T obj) {
        T result = (T) redisTemplate.execute(new RedisCallback<T>() {
            public T doInRedis(RedisConnection connection)
                    throws DataAccessException {
                ValueOperations<String, T> operations = redisTemplate.opsForValue();
                T user = (T) operations.get(key);
                return user;
            }
        });
        return result;
    }*/

}
