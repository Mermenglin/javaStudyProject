package com.examination.produceAndCunsumeAli;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: meimengling
 * @Date: 2019/10/11 9:57
 */
public class Main {

    public static void main(String[] args) {
        BlockingQueue <Integer> queue = new ArrayBlockingQueue<>(100);

        Object lock = new Object();

        Thread threadA = new Thread(new Produce(queue));
        threadA.setName("threadA");
        threadA.start();

        Thread threadB = new Thread(new Cunsume(queue, lock, num -> num%3==0));
        threadB.setName("threadB");

        Thread threadC = new Thread(new Cunsume(queue, lock, num -> num%5==0&&num%3!=0));
        threadC.setName("threadC");

        Thread threadD = new Thread(new Cunsume(queue, lock, num -> num%5!=0&&num%3!=0));
        threadD.setName("threadD");

        threadB.start();
        threadC.start();
        threadD.start();

    }

}
