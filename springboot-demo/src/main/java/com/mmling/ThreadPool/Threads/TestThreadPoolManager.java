package com.mmling.ThreadPool.Threads;

import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: meimengling
 * @Date: 2019/9/10 10:36
 */
@Component
public class TestThreadPoolManager {

    // 线程池维护线程的最少数量
    private final static int CORE_POOL_SIZE = 1;
    // 线程池维护线程的最大数量
    private final static int MAX_POOL_SIZE = 1;
    // 线程池维护线程所允许的空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 10;

    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(WORK_QUEUE_SIZE));


    /**将任务加入订单线程池*/
    public void addOrders(String orderId){
        System.out.println("此订单准备添加到线程池，订单号：" + orderId);

        BusinessThread businessThread = new BusinessThread(orderId);
        threadPool.execute(businessThread);

    }

    /**
     * 查询线程队列中还有多少任务
     * @return
     */
    public long getQueueSize() {
        /**
         *     System.out.println();
         *     int queueSize = tpe.getQueue().size();
         *     System.out.println("当前排队任务数："+ queueSize);
         *
         *     int activeCount = tpe.getActiveCount();
         *     System.out.println("当前活动线程数："+ activeCount);
         *
         *     long completedTaskCount = tpe.getCompletedTaskCount();
         *     System.out.println("执行完成线程数："+ completedTaskCount);
         *
         *     long taskCount = tpe.getTaskCount();
         *     System.out.println("总线程数："+ taskCount);
         */
        return threadPool.getQueue().size();
    }
}

//@Component
class BusinessThread implements Runnable{

    private String acceptStr;

    public BusinessThread(String acceptStr) {
        this.acceptStr = acceptStr;
    }

    @Override
    public void run() {
        //业务操作
        System.out.println("多线程已经处理订单插入系统，订单号："+acceptStr);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
