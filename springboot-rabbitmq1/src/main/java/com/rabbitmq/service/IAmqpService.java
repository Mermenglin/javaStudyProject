package com.rabbitmq.service;

/**
 * @Author: meimengling
 * @Date: 2019/7/24 17:49
 */
public interface IAmqpService {
    void convertAndSend(String message);
}
