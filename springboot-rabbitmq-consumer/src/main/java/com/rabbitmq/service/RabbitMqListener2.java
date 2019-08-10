package com.rabbitmq.service;

/**
 * @Author: meimengling
 * @Date: 2019/8/10 16:56
 */

import com.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;


/**
 * 注解绑定 Exchange 和 Queue
 */

@Configuration
@RabbitListener(bindings = @QueueBinding(
        exchange = @Exchange(value = "directExchange", autoDelete = "true"),
        value = @Queue(value = "direct.second"),
        key = "directKey2"))
public class RabbitMqListener2 {

    @RabbitHandler
    public void handler(String message) {
        System.out.println(message);
    }

    @RabbitHandler
    public void handler(@Payload User message) {
        System.out.println(message);
    }
}

