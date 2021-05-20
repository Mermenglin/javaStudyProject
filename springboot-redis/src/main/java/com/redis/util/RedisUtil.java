package com.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @Author: meimengling
 * @Date: 2019/9/25 15:49
 */
@Component
public class RedisUtil {

    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";


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

    /**
     * 单机、主从redis可用redis分布式锁
     * @param lockKey
     * @param clientId  UUID
     * @param seconds   最长锁时间
     * @return
     */
    public Boolean getLock(String lockKey, String clientId, long seconds) {
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            String result = jedis.set(lockKey, clientId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, seconds);
            if (LOCK_SUCCESS.equals(result)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        });
    }

    public Boolean releaseLock(String lockKey, String clientId) {
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            Object result = jedis.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(lockKey),
                    Collections.singletonList(clientId));
            if (RELEASE_SUCCESS.equals(result)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        });
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
