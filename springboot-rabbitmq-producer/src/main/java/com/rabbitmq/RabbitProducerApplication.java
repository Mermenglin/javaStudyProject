package com.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: meimengling
 * @Date: 2019/7/24 17:53
 */
@SpringBootApplication
public class RabbitProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitProducerApplication.class, args);
    }
}
