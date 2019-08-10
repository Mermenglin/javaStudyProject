package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: meimengling
 * @Date: 2019/8/10 15:40
 */
@Configuration
public class BindingConfig {
    public final static String first = "direct.first";
    public final static String Exchange_NAME = "directExchange";
    public final static String RoutingKey1 = "directKey1";

    @Bean
    public Queue queueFirst() {
        return new Queue(first);
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

    // 设置使用 Jackson2JsonMessageConverter 反序列化
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
