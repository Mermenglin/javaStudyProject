package com.mmling.springbootschedule.registar;

import com.mmling.springbootschedule.task.ScheduledTask;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 添加定时任务注册类，用来增加、删除定时任务。
 *
 * @author Meimengling
 * @date 2021-7-30 18:48
 */
@Component
public class CronTaskRegistar implements DisposableBean {
    private final Map<Integer, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    @Autowired
    private TaskScheduler taskScheduler;

    public TaskScheduler getScheduler() {
        return this.taskScheduler;
    }

    public void addCronTask(Integer cronId, Runnable task, String cronExpression) {
        addCronTask(cronId, new CronTask(task, cronExpression));
    }

    public void addCronTask(Integer cronId, CronTask cronTask) {
        if (cronTask != null) {
            if (this.scheduledTasks.containsKey(cronId)) {
                removeCronTask(cronId);
            }
            this.scheduledTasks.put(cronId, scheduleCronTask(cronTask));
        }
    }

    public void removeCronTask(Integer cronId) {
        ScheduledTask scheduledTask = this.scheduledTasks.remove(cronId);
        if (scheduledTask != null) {
            scheduledTask.cancel();
        }
    }

    public ScheduledTask scheduleCronTask(CronTask cronTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }


    @Override
    public void destroy() {
        for (ScheduledTask task : this.scheduledTasks.values()) {
            task.cancel();
        }
        this.scheduledTasks.clear();
    }
}
