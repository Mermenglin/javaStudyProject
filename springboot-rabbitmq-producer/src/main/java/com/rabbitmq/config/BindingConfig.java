package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: meimengling
 * @Date: 2019/8/10 15:40
 */
@Configuration
public class BindingConfig {
    public final static String first = "direct.first";
    public final static String second = "direct.second";
    public final static String Exchange_NAME = "directExchange";
    public final static String RoutingKey1 = "directKey1";
    public final static String RoutingKey2 = "directKey2";

    @Bean
    public Queue queueFirst() {
        return new Queue(first);
    }

    @Bean
    public Queue queueSecond() {
        return new Queue(second);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(Exchange_NAME, true, true);
    }

    //利用BindingBuilder绑定Direct与queueFirst
    @Bean
    public Binding bindingExchangeFirst(Queue queueFirst, DirectExchange directExchange) {
        return BindingBuilder.bind(queueFirst).to(directExchange).with(RoutingKey1);
    }

    //利用BindingBuilder绑定Direct与queueSecond
    @Bean
    public Binding bindingExchangeSecond(Queue queueSecond, DirectExchange directExchange) {
        return BindingBuilder.bind(queueSecond).to(directExchange).with(RoutingKey2);
    }
}
