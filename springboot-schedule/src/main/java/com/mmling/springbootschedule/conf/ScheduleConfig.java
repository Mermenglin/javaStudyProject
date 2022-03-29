package com.mmling.springbootschedule.conf;

import com.mmling.springbootschedule.controller.CronController;
import com.mmling.springbootschedule.entity.Cron;
import com.mmling.springbootschedule.registar.CronTaskRegistar;
import com.mmling.springbootschedule.task.SchedulingRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Meimengling
 * @date 2021-7-30 18:54
 */
@Component
public class ScheduleConfig implements CommandLineRunner {

    @Autowired
    private CronTaskRegistar taskRegistar;

    @Autowired
    private CronController cronController;

    @Override
    public void run(String... args) throws Exception {
//        List<Cron> crons = cronController.findCrons();
//        if (crons != null) {
//            for (Cron cron : crons) {
//                if (cron.getStatus() == 1) {
//                    SchedulingRunnable task = new SchedulingRunnable(cron);
//                    taskRegistar.addCronTask(cron.getId(), task, cron.getCronStr());
//                }
//            }
//        }
    }
}
