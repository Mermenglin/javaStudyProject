package com.rabbitmq.service;

/**
 * @Author: meimengling
 * @Date: 2019/8/10 16:55
 */

import com.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * BindingConfig 类绑定 Exchange 和 Queue
 */
@Configuration
@RabbitListener(queues = "direct.first")
public class RabbitMqListener {

    @RabbitHandler
    public void handler(String message) {
        System.out.println(message);
    }

    @RabbitHandler
    public void handler(@Payload User message) {
        System.out.println(message);
    }
}
