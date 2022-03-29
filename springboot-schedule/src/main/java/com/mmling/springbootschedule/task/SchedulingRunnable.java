package com.mmling.springbootschedule.task;

import com.mmling.springbootschedule.entity.Cron;
import com.mmling.springbootschedule.registar.FixedRateRegistar;
import com.mmling.springbootschedule.service.ICronService;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Meimengling
 * @date 2021-7-30 18:48
 */
@Slf4j
public class SchedulingRunnable  implements Runnable {
    private Cron cron;
    private ICronService service;
    private FixedRateRegistar fixedRateRegistar;

    public SchedulingRunnable(Cron cron, ICronService service, FixedRateRegistar fixedRateRegistar) {
        this.cron = cron;
        this.service = service;
        this.fixedRateRegistar = fixedRateRegistar;
    }

    @Override
    public void run() {
        if (service.getStatus()) {
            log.info("状态变化，需要停止任务");
            fixedRateRegistar.removeFixedRateTask(cron.getId());
        } else {
            //执行任务
            log.info(cron.getId() + ":执行任务");
        }
    }
}
