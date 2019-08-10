package com.rabbitmq.service.impl;

import com.rabbitmq.service.IAmqpHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: meimengling
 * @Date: 2019/7/24 17:52
 */
@Service
@Slf4j
public class IAmqpHelloServiceImpl implements IAmqpHelloService {
    @Override
    public void receiveHelloQueue(String message) {
        log.info("IAmqpHelloServiceImpl---业务实现类消费"+message);
    }
}
