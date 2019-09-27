package com.ThreadCount.demo;

import com.ThreadCount.demo.service.HelloService;
import com.ThreadCount.demo.util.CountDownLatchUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: meimengling
 * @Date: 2019/4/16 10:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    public void testSayHello() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        //模拟1000个线程并发
        CountDownLatchUtil countDownLatchUtil = new CountDownLatchUtil(1000);

        /**
         * 函数式编程，自动实现 MyFunctionalInterface 接口，并实现了其中的run方法。
         */
        countDownLatchUtil.latch(() -> {
            helloService.sayHello(currentTimeMillis);
        });
    }

}
