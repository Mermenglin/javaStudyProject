package com.mmling.TimeWheel;

import java.util.concurrent.DelayQueue;

/**
 * 时间轮主数据结构
 *
 * @author Meimengling
 * @date 2022-3-31 16:23
 */
public class TimeWheel {
    /**
     * 一个槽的时间间隔(时间轮最小刻度)
     */
    private long tickMs;
    /**
     * 时间轮大小(槽的个数)
     */
    private int wheelSize;
    /**
     * 一轮的时间跨度
     */
    private long interval;

    private long currentTime;
    /**
     * 槽
     */
    private TimerTaskList[] buckets;
    /**
     * 上层时间轮
     */
    private volatile TimeWheel overflowWheel;
    /**
     * 一个timer只有一个delayqueue
     */
    private DelayQueue delayQueue;

    public TimeWheel(long tickMs, int wheelSize, long currentTime, DelayQueue delayQueue) {
        this.currentTime = currentTime;
        this.tickMs = tickMs;
        this.wheelSize = wheelSize;
        this.interval = tickMs * wheelSize;
        this.buckets = new TimerTaskList[wheelSize];
        this.currentTime = currentTime - (currentTime % tickMs);
        this.delayQueue = delayQueue;
        for (int i = 0; i < wheelSize; i++) {
            buckets[i] = new TimerTaskList();
        }
    }

    public boolean add(TimerTaskEntry entry) {
        long expiration = entry.getExpireMs();
        if (expiration < tickMs + currentTime) {
            //到期了
            return false;
        } else if (expiration < currentTime + interval) {
            //扔进当前时间轮的某个槽里,只有时间大于某个槽,才会放进去
            long virtualId = (expiration / tickMs);
            int index = (int) (virtualId % wheelSize);
            TimerTaskList bucket = buckets[index];
            bucket.addTask(entry);
            //设置bucket 过期时间
            if (bucket.setExpiration(virtualId * tickMs)) {
                //设好过期时间的bucket需要入队
                delayQueue.offer(bucket);
                return true;
            }
        } else {
            //当前轮不能满足,需要扔到上一轮
            TimeWheel timeWheel = getOverflowWheel();
            return timeWheel.add(entry);
        }
        return false;
    }

    private TimeWheel getOverflowWheel() {
        if (overflowWheel == null) {
            synchronized (this) {
                if (overflowWheel == null) {
                    overflowWheel = new TimeWheel(interval, wheelSize, currentTime, delayQueue);
                }
            }
        }
        return overflowWheel;
    }

    /**
     * 推进指针
     *
     * @param timestamp
     */
    public void advanceLock(long timestamp) {
        if (timestamp > currentTime + tickMs) {
            currentTime = timestamp - (timestamp % tickMs);
            if (overflowWheel != null) {
                this.getOverflowWheel().advanceLock(timestamp);
            }
        }
    }
}
