package com.mmling.mybatisRedisCache.job;

import com.mmling.mybatisRedisCache.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.DelayQueue;

/**
 * @author meimengling
 * @version 1.0
 * @date 2020-4-16 14:46
 */
@Slf4j
public class OrderExecutionTimer implements Runnable {

    DelayQueue<OrderDelay> queue = new DelayQueue<>();

    OrderService orderService;


    public OrderExecutionTimer(DelayQueue<OrderDelay> queue, OrderService orderService) {
        this.queue = queue;
        this.orderService = orderService;
    }

    @Override
    public void run() {
        while (true) {
            OrderDelay order = null;
            try {
                order = queue.take();
                log.info("订单编码为 id = {}", order.getId());
                orderService.removeOrder(order);
            } catch (InterruptedException e) {
                log.error("从订单列表中获取订单失败, message={}", e);
            }
        }
    }


}
