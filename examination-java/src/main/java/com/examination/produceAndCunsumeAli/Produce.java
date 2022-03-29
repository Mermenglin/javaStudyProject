package com.examination.produceAndCunsumeAli;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: meimengling
 * @Date: 2019/10/11 9:53
 */
public class Produce implements Runnable {

    private BlockingQueue<Integer> queues;

    private static Random randomMi = new Random();

    private int num;

    public Produce(BlockingQueue<Integer> queues) {
        this.queues = queues;
        this.num = 1;
    }

    @Override
    public void run() {
        while (num < 101) {
            int missecond = randomMi.nextInt(180) + 10;
            try {
                queues.put(num++);
                Thread.sleep(missecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "执行结束");
    }
}
