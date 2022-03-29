package com.mmling.springbootschedule.controller;

import com.mmling.springbootschedule.entity.Cron;
import com.mmling.springbootschedule.registar.CronTaskRegistar;
import com.mmling.springbootschedule.registar.FixedRateRegistar;
import com.mmling.springbootschedule.service.ICronService;
import com.mmling.springbootschedule.task.SchedulingRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @author Meimengling
 * @date 2021-7-30 18:55
 */
@RestController
public class CronController {
    @Autowired
    private ICronService cronService;

    @Autowired
    private CronTaskRegistar taskRegistrar;

    @Autowired
    private FixedRateRegistar fixedRateRegistar;

    //添加定时任务
    @PostMapping("/saveCron")
    public String save() {
        Cron cron = new Cron();
        //每10秒执行一次
        cron.setCronStr("*/5 * * * * ? ");
        cron.setStatus(1);
        cron.setSendTime("08:15");
        boolean success = cronService.saveOrUpdate(cron);
        if (!success) {
            return "定时任务保存失败";
        }
        Random r = new Random();
        cron.setId(r.nextInt());
        if (cron.getStatus() == 1) {
            SchedulingRunnable task = new SchedulingRunnable(cron, cronService, fixedRateRegistar);
            taskRegistrar.removeCronTask(cron.getId());
            taskRegistrar.addCronTask(cron.getId(), task, cron.getCronStr());
        }
        if (cron.getStatus() == 0) {
            taskRegistrar.removeCronTask(cron.getId());
        }
        return "定时任务保存成功" + cron.getId();
    }

    //删除定时任务
    @PostMapping("/deleteCron")
    public String delete(Integer cronId) {
        if (cronId != null) {
            Cron cron = cronService.getById(cronId);
            boolean remove = cronService.removeById(cronId);
            if (remove) {
                taskRegistrar.removeCronTask(cronId);
            }
            return "定时任务删除成功";
        }
        return "传入数据为空";
    }

    @PostMapping("/refreshTask")
    public String refreshTask(Integer taskId) {
        if (taskId != null) {
            Cron taskInfo = new Cron();
            taskInfo.setId(taskId);
            SchedulingRunnable task = new SchedulingRunnable(taskInfo, cronService, fixedRateRegistar);
            fixedRateRegistar.addFixedRateTask(taskInfo.getId(), task, 10L);
            return "定时任务刷新成功";
        }
        return "传入数据为空";
    }

    @PostMapping("/setStatus")
    public String setStatus() {
        cronService.setStatus(true);
        return "修改状态成功";
    }

    public List<Cron> findCrons() {
        return cronService.list();
    }
}
