package com.mmling.TimeWheel;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定时器实现
 *
 * @author Meimengling
 * @date 2022-3-31 16:48
 */
@Slf4j
public class SystemTimer implements Timer {
    /**
     * 底层时间轮
     */
    private TimeWheel timeWheel;
    /**
     * 一个Timer只有一个延时队列
     */
    private DelayQueue<TimerTaskList> delayQueue = new DelayQueue<>();
    /**
     * 过期任务执行线程
     */
    private ExecutorService workerThreadPool;
    /**
     * 轮询delayQueue获取过期任务线程
     */
    private ExecutorService bossThreadPool;

    public SystemTimer() {
        this.timeWheel = new TimeWheel(1, 20, System.currentTimeMillis(), delayQueue);
        this.workerThreadPool = new ThreadPoolExecutor(100, 100,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        this.bossThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        //20ms推动一次时间轮运转
        this.bossThreadPool.submit(() -> {
            for (; ; ) {
                this.advanceClock(20);
            }
        });
    }

    public void addTimerTaskEntry(TimerTaskEntry entry) {
        if (!timeWheel.add(entry)) {
            //已经过期了
            TimerTask timerTask = entry.getTimerTask();
            log.info("=====任务:{} 已到期,准备执行============", timerTask.getDesc());
            workerThreadPool.submit(timerTask);
        }
    }

    @Override
    public void add(TimerTask timerTask) {
        log.info("=======添加任务开始====task:{}", timerTask.getDesc());
        TimerTaskEntry entry = new TimerTaskEntry(timerTask, timerTask.getDelayMs() + System.currentTimeMillis());
        timerTask.setTimerTaskEntry(entry);
        addTimerTaskEntry(entry);
    }

    /**
     * 推动指针运转获取过期任务
     *
     * @param timeout
     */
    @Override
    public void advanceClock(long timeout) {
        try {
            TimerTaskList bucket = delayQueue.poll(timeout, TimeUnit.MILLISECONDS);
            if (bucket != null) {
                //推进时间
                timeWheel.advanceLock(bucket.getExpiration());
                //执行过期任务(包含降级)
                bucket.clear(this::addTimerTaskEntry);
            }
        } catch (InterruptedException e) {
            log.error("advanceClock error");
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void shutdown() {
        this.bossThreadPool.shutdown();
        this.workerThreadPool.shutdown();
        this.timeWheel = null;
    }
}
