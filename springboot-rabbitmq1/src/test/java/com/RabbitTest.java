package com;

import com.rabbitmq.RabbitApplication;
import com.rabbitmq.service.IAmqpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: meimengling
 * @Date: 2019/7/24 17:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class RabbitTest {

    @Autowired
    private IAmqpService amqpService;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            String message = "Hello测试信息:" + i;
            amqpService.convertAndSend(message);
        }
    }
}
