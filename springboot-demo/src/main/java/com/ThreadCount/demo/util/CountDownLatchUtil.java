package com.ThreadCount.demo.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: meimengling
 * @Date: 2019/4/16 10:11
 */
public class CountDownLatchUtil {

    private CountDownLatch start;
    private CountDownLatch end;
    private int pollSize = 10;

    public CountDownLatchUtil() {
        this(10);
    }

    public CountDownLatchUtil(int pollSize) {
        this.pollSize = pollSize;
        start = new CountDownLatch(1);
        end = new CountDownLatch(pollSize);
    }

    public void latch(MyFunctionalInterface functionalInterface) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < pollSize; i++) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        start.await();
                        functionalInterface.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            };
            executorService.submit(run);
        }

        start.countDown();
        end.await();
        executorService.shutdown();
    }

    /**
     * 函数式编程接口，只能有一个抽象方法。
     */
    @FunctionalInterface
    public interface MyFunctionalInterface {
        void run();
    }
}