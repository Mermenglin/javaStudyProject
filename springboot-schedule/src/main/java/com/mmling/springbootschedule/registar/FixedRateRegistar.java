package com.mmling.springbootschedule.registar;

import com.mmling.springbootschedule.task.ScheduledTask;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.FixedRateTask;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Meimengling
 * @date 2021-8-2 10:26
 */
@Component
public class FixedRateRegistar implements DisposableBean {

    private final Map<Integer, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    @Autowired
    private TaskScheduler taskScheduler;

    public TaskScheduler getScheduler() {
        return this.taskScheduler;
    }

    public void addFixedRateTask(Integer taskId, Runnable task, Long fixedRate) {
        addFixedRateTask(taskId, new FixedRateTask(task, fixedRate * 1000, fixedRate * 1000));
    }

    public void addFixedRateTask(Integer taskId, FixedRateTask fixedRateTask) {
        if (fixedRateTask != null) {
            if (this.scheduledTasks.containsKey(taskId)) {
                removeFixedRateTask(taskId);
            }
            this.scheduledTasks.put(taskId, scheduleCronTask(fixedRateTask));
        }
    }

    public void removeFixedRateTask(Integer taskId) {
        ScheduledTask scheduledTask = this.scheduledTasks.remove(taskId);
        if (scheduledTask != null) {
            scheduledTask.cancel();
        }
    }

    public ScheduledTask scheduleCronTask(FixedRateTask fixedRateTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        Date startDate = getDelayDate(fixedRateTask.getInitialDelay());
        scheduledTask.future = this.taskScheduler.scheduleAtFixedRate(fixedRateTask.getRunnable(),
                startDate, fixedRateTask.getInterval());
        return scheduledTask;
    }

    private Date getDelayDate(long initialDelay) {
        Date date = new Date();
        date.setTime(date.getTime() + initialDelay);
        return date;
    }


    @Override
    public void destroy() throws Exception {
        for (ScheduledTask task : this.scheduledTasks.values()) {
            task.cancel();
        }
        this.scheduledTasks.clear();
    }
}
