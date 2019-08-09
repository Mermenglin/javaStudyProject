package com.rabbitmq.utils;

import java.io.Serializable;

/**
 * @Author: meimengling
 * @Date: 2019/7/24 17:53
 */
public class QueueConstant implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 类型
     */
    public static final String EXCHANGE_FANOUT = "com.exchange.fanout"; // 不处理路由键
    public static final String EXCHANGE_TOPIC = "com.exchange.topic"; // 主题路由键
    public static final String EXCHANGE_DIRECT = "com.exchange.direct"; // 处理路由键

    /**
     * 队列
     */
    public static final String QUEUE_NOTIFY_HELLO = "com.queue.notify.hello"; // 定义的hello通知队列
}
