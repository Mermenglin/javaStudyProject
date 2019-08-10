package com.rabbitmq.controller;

import com.rabbitmq.config.BindingConfig;
import com.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * @Author: meimengling
 * @Date: 2019/8/10 16:07
 */
@Controller
@RequestMapping("/producer")
public class ProducerController {
    @Autowired
    private RabbitTemplate template;

    @RequestMapping("/send")
    public void send() {
        // JSON 格式传输
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        for(int n=0;n<100;n++){
            template.convertAndSend(BindingConfig.Exchange_NAME,BindingConfig.RoutingKey1,"I'm the first queue!   "+String.valueOf(n),getCorrelationData());
            template.convertAndSend(BindingConfig.Exchange_NAME,BindingConfig.RoutingKey2,"I'm the second queue!  "+String.valueOf(n),getCorrelationData());
        }
    }

    @RequestMapping("/sendUser")
    public void sendUser() {
        // JSON 格式传输
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        for(int n=0;n<100;n++){
            User user = new User("小明" + n, n);
            template.convertAndSend(BindingConfig.Exchange_NAME,BindingConfig.RoutingKey1,user,getCorrelationData());
            template.convertAndSend(BindingConfig.Exchange_NAME,BindingConfig.RoutingKey2,user,getCorrelationData());
        }
    }

    private CorrelationData getCorrelationData(){
        return new CorrelationData(UUID.randomUUID().toString());
    }
}
