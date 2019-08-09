package com.example.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @Author: meimengling
 * @Date: 2019/4/9 10:13
 */
@Slf4j
@Component
public class RedisLockTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * redisTemplate设置值，使用的是setEX，不管redis中是否有该key值，都会设置成功，
     * 会覆盖之前的value，所以慎用。
     * @param key
     * @param value
     * @param expireTime 超时时间，单位是秒
     * @return
     */
    public  boolean setEx(String key, String value, int expireTime) {
        boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            connection.setEx(key.getBytes(), expireTime, value.getBytes());
            return true;
        });
        return result;
    }

    /**
     * redisTemplate设置值，使用的是 setNX，当redis中有该key，则设置失败
     * @param key
     * @param value
     * @return
     */
    public boolean setNx(String key, String value) {
        boolean result = redisTemplate.execute(
                (RedisCallback<Boolean>) connection -> connection.setNX(key.getBytes(), value.getBytes()));
        return result;
    }

    /**
     * jedis 方式设置值，并同时设置超时时间，可用于分布式锁
     * @param key
     * @param value
     * @param expireTime    超时时间，单位是毫秒
     * @return
     */
    public boolean set(String key, String value, int expireTime){
        Jedis jedis = new Jedis();
//        jedis.auth("password");     // 设置连接密码
        String set = jedis.set(key, value, "NX", "PX", expireTime);
        log.info("------------------------------result is :" + set);
        if (set.equals("OK"))
            return true;
        return false;
    }

    public void put(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }
}
