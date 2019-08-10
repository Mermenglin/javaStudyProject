package com.rabbitmq.service.impl;

import com.rabbitmq.service.IAmqpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: meimengling
 * @Date: 2019/7/24 17:49
 */
@Service
@Slf4j
public class IAmqpServiceImpl implements IAmqpService {

    private @Autowired
    AmqpTemplate amqpTemplate;

    @Override
    public void convertAndSend(String message) {
        log.info("消息发送--" + message);
        amqpTemplate.convertAndSend("com.queue.notify.hello1", message);
    }

}