package com.rabbitmq.mq;

import com.rabbitmq.service.IAmqpHelloService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: meimengling
 * @Date: 2019/7/24 17:50
 */
@Slf4j
@Component
public class AmqpServiceConsumer {
    @Autowired
    private IAmqpHelloService amqpHelloService;

    public AmqpServiceConsumer() {
    }

    @RabbitListener(queues = {"com.queue.notify.hello1"})
    public void receiveSmsCodeQueue(String message) {
        log.info("------hello：消费者处理消息------");
//        log.debug(message);
        this.amqpHelloService.receiveHelloQueue(message);
    }
}
