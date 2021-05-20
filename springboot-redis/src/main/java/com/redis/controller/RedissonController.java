package com.redis.controller;

import com.redis.util.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author meimengling
 * @version 1.0
 * @date 2021-5-20 13:57
 */
@RestController
@RequestMapping("/v1/redisson")
@Slf4j
public class RedissonController {
    /**
     * 锁测试共享变量
     */
    private Integer lockCount = 10;
    /**
     * 无锁测试共享变量
     */
    private Integer count = 10;
    /**
     * 模拟线程数
     */
    private static int threadNum = 10;

    @GetMapping("/test")
    public void lock(){
        // 计数器
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < threadNum; i ++) {
            MyRunnable myRunnable = new MyRunnable(countDownLatch);
            Thread myThread = new Thread(myRunnable);
            myThread.start();
        }
        // 释放所有线程
        countDownLatch.countDown();
    }

    public class MyRunnable implements Runnable {
        /**
         * 计数器
         */
        final CountDownLatch countDownLatch;
        public MyRunnable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            try {
                // 阻塞当前线程，直到计时器的值为0
                countDownLatch.await();
            } catch (InterruptedException e) {
                log.error(e.getMessage(),e);
            }
            // 无锁操作
            testCount();
            // 加锁操作
            testLockCount();
        }
    }

    private void testLockCount() {
        String lockKey = "lock-test";
        try {
            RedisLockUtil.lock(lockKey, 2, TimeUnit.SECONDS);
            lockCount--;
            log.info("lockCount = {}", lockCount);
        } finally {
            RedisLockUtil.unlock(lockKey);
        }
    }

    private void testCount() {
        count--;
        log.info("count值："+count);
    }

}
