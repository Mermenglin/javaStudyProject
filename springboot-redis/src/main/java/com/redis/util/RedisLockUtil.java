package com.redis.util;

import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;

import java.util.concurrent.TimeUnit;

/**
 * @author meimengling
 * @version 1.0
 * @date 2021-5-20 13:43
 */
public class RedisLockUtil {

    private static RedisDistributedLocker redisLocker = ApplicationContextHolder.getBean(RedisDistributedLocker.class);

    /**
     * 加锁
     *
     * @param lockKey
     * @return
     */
    public static RLock lock(String lockKey) {
        return redisLocker.lock(lockKey);
    }


    /**
     * 释放锁
     *
     * @param lockKey
     */

    public static void unlock(String lockKey) {
        redisLocker.unlock(lockKey);
    }


    /**
     * 释放锁
     *
     * @param lock
     */

    public static void unlock(RLock lock) {
        redisLocker.unlock(lock);
    }


    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */

    public static RLock lock(String lockKey, int timeout) {
        return redisLocker.lock(lockKey, timeout);
    }


    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param unit    时间单位
     * @param timeout 超时时间
     */

    public static RLock lock(String lockKey, int timeout, TimeUnit unit) {
        return redisLocker.lock(lockKey, unit, timeout);
    }


    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */

    public static boolean tryLock(String lockKey, int waitTime, int leaseTime) {
        return redisLocker.tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
    }


    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param unit      时间单位
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */

    public static boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        return redisLocker.tryLock(lockKey, unit, waitTime, leaseTime);
    }


    /**
     * 获取计数器
     *
     * @param name
     * @return
     */
    public static RCountDownLatch getCountDownLatch(String name) {
        return redisLocker.getCountDownLatch(name);
    }


    /**
     * 获取信号量
     *
     * @param name
     * @return
     */
    public static RSemaphore getSemaphore(String name) {
        return redisLocker.getSemaphore(name);
    }
}
