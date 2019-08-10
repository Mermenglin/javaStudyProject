package com.rabbitmq.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: meimengling
 * @Date: 2019/7/19 10:45
 */

@Configuration
public class ConnectionConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
//    @Value("${spring.rabbitmq.publisher-confirms}")
//    private boolean publisherConfirms;

    public ConnectionConfig() {
    }

    @Bean
    public ConnectionFactory getConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(this.host);
        connectionFactory.setPort(this.port);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);
//        connectionFactory.setPublisherConfirms(this.publisherConfirms);
        return connectionFactory;
    }
}