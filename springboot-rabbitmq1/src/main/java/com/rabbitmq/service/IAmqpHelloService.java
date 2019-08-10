package com.rabbitmq.service;

/**
 * @Author: meimengling
 * @Date: 2019/7/24 17:52
 */
public interface IAmqpHelloService {
    void receiveHelloQueue(String message);
}
