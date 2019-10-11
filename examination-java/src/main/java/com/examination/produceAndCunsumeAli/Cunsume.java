package com.examination.produceAndCunsumeAli;

import java.util.concurrent.BlockingQueue;

/**
 * @Author: meimengling
 * @Date: 2019/10/11 9:46
 */
public class Cunsume implements Runnable {
    BlockingQueue<Integer> queues;
    Object lock;
    ConditionEvluate conditionEvluate;

    public Cunsume(BlockingQueue<Integer> queues, Object lock, ConditionEvluate conditionEvluate) {
        this.queues = queues;
        this.lock = lock;
        this.conditionEvluate = conditionEvluate;
    }

    @Override
    public void run() {
        while (true) {
            Integer num = queues.peek();
            if (num == null) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (conditionEvluate.vallid(num)) {
                synchronized (lock) {
                    try {
                        num = queues.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ",输出" + num);
                }
            }
            if (num > 99) break;
        }
        System.out.println(Thread.currentThread().getName() + "执行结束");
    }
}
