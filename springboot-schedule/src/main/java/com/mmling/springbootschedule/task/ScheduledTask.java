package com.mmling.springbootschedule.task;

import java.util.concurrent.ScheduledFuture;

/**
 * @author Meimengling
 * @date 2021-7-30 18:47
 */
public final class ScheduledTask {
    public volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
