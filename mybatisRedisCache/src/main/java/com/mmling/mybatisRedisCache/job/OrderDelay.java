package com.mmling.mybatisRedisCache.job;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 订单延时类
 *
 * @author meimengling
 * @version 1.0
 * @date 2020-4-16 10:04
 */
public class OrderDelay implements Delayed {
    private Long id;
    private long excuteTime;//执行时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(long excuteTime) {
        this.excuteTime = excuteTime;
    }

    public OrderDelay(Long id, long excuteTime) {
        this.id = id;
        this.excuteTime = excuteTime;
    }

    public OrderDelay(Long id) {
        this.id = id;
    }

    /**
     * 过期时间减去当前时间
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return this.excuteTime - System.currentTimeMillis();
    }

    /**
     * 根据订单过期时间排序
     *
     * @param delayed
     * @return
     */
    @Override
    public int compareTo(Delayed delayed) {
        return Long.valueOf(this.excuteTime).compareTo(Long.valueOf(((OrderDelay) delayed).excuteTime));
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((OrderDelay) obj).id);
    }
}
