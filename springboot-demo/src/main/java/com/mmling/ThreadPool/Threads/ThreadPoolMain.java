package com.mmling.ThreadPool.Threads;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: meimengling
 * @Date: 2019/9/11 11:45
 */
public class ThreadPoolMain {

    public static void main(String[] args) {
        TestThreadPoolManager testThreadPoolManager = new TestThreadPoolManager();

        for (int i = 0; i < 10; i++) {
            testThreadPoolManager.addOrders("orderId:  " + i);
//            System.out.println("QueueSize:   " + testThreadPoolManager.getQueueSize());
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long queueSize = testThreadPoolManager.getQueueSize();
                System.out.println("QueueSize:   " + queueSize);
                if (queueSize == 0) {
                    System.out.println("多线程任务处理完成");
                    timer.cancel();
                    System.exit(1);
                }
            }
        }, 3000, 1000);

    }
}
