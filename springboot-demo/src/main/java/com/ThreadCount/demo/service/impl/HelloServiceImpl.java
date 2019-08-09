package com.ThreadCount.demo.service.impl;

import com.ThreadCount.demo.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: meimengling
 * @Date: 2019/4/16 10:13
 */
@Service
public class HelloServiceImpl implements HelloService {

    private final Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Transactional
    @Override
    public void sayHello(long timeMillis) {
        log.info("#IN sayHello");
        long time = System.currentTimeMillis() - timeMillis;
        if (time > 5000) {
            //超过5秒的打印日志输出
            log.warn("time : {}", time);
        }
        try {
            //模拟业务执行时间为1s
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("# OUT sayHello");
    }
}
