package com.example.demo.test;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @Author: meimengling
 * @Date: 2019/4/9 14:19
 */
public interface DistributeLock {

    RLock lock(String lockKey);

    RLock lock(String lockKey, int timeout);

    RLock lock(String lockKey, TimeUnit unit, int timeout);

    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);
}
