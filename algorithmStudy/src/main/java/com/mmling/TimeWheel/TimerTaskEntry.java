package com.mmling.TimeWheel;

import lombok.Data;

/**
 * 存储任务的容器entry
 *
 * @author Meimengling
 * @date 2022-3-31 16:44
 */
@Data
public class TimerTaskEntry implements Comparable<TimerTaskEntry> {
    private TimerTask timerTask;
    /**
     * 执行时间的时间戳
     */
    private long expireMs;
    volatile TimerTaskList timedTaskList;
    TimerTaskEntry next;
    TimerTaskEntry prev;

    public TimerTaskEntry(TimerTask timedTask, long expireMs) {
        this.timerTask = timedTask;
        this.expireMs = expireMs;
        this.next = null;
        this.prev = null;
    }

    void remove() {
        TimerTaskList currentList = timedTaskList;
        while (currentList != null) {
            currentList.remove(this);
            currentList = timedTaskList;
        }
    }

    @Override
    public int compareTo(TimerTaskEntry o) {
        return ((int) (this.expireMs - o.expireMs));
    }
}
