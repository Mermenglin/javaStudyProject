package com.mmling.TimeWheel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 任务包装类(这里也可以将工作任务以线程变量的方式去传入)
 *
 * @author Meimengling
 * @date 2022-3-31 17:03
 */
@Data
@Slf4j
public class TimerTask implements Runnable {
    /**
     * 延时时间
     */
    private long delayMs;
    /**
     * 任务所在的entry
     */
    private TimerTaskEntry timerTaskEntry;

    private String desc;

    public TimerTask(String desc, long delayMs) {
        this.desc = desc;
        this.delayMs = delayMs;
        this.timerTaskEntry = null;
    }

    public synchronized void setTimerTaskEntry(TimerTaskEntry entry) {
        // 如果这个timetask已经被一个已存在的TimerTaskEntry持有,先移除一个
        if (timerTaskEntry != null && timerTaskEntry != entry) {
            timerTaskEntry.remove();
        }
        timerTaskEntry = entry;
    }

    public TimerTaskEntry getTimerTaskEntry() {
        return timerTaskEntry;
    }

    @Override
    public void run() {
        log.info("============={}任务执行", desc);
    }
}