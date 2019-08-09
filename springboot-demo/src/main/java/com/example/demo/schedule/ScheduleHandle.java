package com.example.demo.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: meimengling
 * @Date: 2019/4/8 11:50
 */
@Component
public class ScheduleHandle {

    private final Logger log = LoggerFactory.getLogger(ScheduleHandle.class);

    private List<Integer> index = Arrays.asList(8*1000, 3*1000, 6*1000, 2*1000, 2*1000);

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /*@Scheduled(fixedDelay = 3*1000)
    private void fixedDelay() throws InterruptedException {
        int i = atomicInteger.get();
        if (i < 5){
            Integer sleepTime = index.get(i);
            log.info("第{}任务开始执行，执行时间为{}ms", i, sleepTime);
            Thread.sleep(sleepTime);
            atomicInteger.getAndIncrement();
        }
    }*/

    @Scheduled(cron = "*/5 * * * * *")
    private void cora() throws InterruptedException {
        int i = atomicInteger.get();
        if (i < 5){
            Integer sleepTime = index.get(i);
            log.info("第{}任务开始执行，执行时间为{}ms", i, sleepTime);
            Thread.sleep(sleepTime);
            atomicInteger.getAndIncrement();
        }
    }

    /*@Scheduled(fixedRate = 5 * 1000)
    private void fixedRate() throws InterruptedException {
        int i = atomicInteger.get();
        if (i < 5){
            Integer sleepTime = index.get(i);
            log.info("第{}个任务开始执行，执行时间为{}ms", i, sleepTime);
            Thread.sleep(sleepTime);
            atomicInteger.getAndIncrement();
        }
    }*/

}
