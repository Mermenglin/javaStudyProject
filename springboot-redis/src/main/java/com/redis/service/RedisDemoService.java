package com.redis.service;

/**
 * @Author: meimengling
 * @Date: 2019/9/25 15:48
 */
public interface RedisDemoService {
    Long incr(String key);

    String get(String key);
}
