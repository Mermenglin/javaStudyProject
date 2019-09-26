package com.redis.service.impl;

import com.redis.service.RedisDemoService;
import com.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: meimengling
 * @Date: 2019/9/25 15:48
 */
@Service
public class RedisDemoServiceImpl implements RedisDemoService {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Long incr(String key) {
        return redisUtil.increment(key, new Long(1));
    }

    @Override
    public String get(String key) {
        return redisUtil.getIncr(key);
    }
}
