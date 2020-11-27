package com.mmling.mybatisRedisCache.job;

import com.mmling.mybatisRedisCache.entity.Order;
import com.mmling.mybatisRedisCache.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;

/**
 * @author meimengling
 * @version 1.0
 * @date 2020-4-16 15:25
 */
@Component
@Lazy(false)
@Slf4j
public class DelayOrderComponent {

    @Autowired
    OrderService orderService;

    private static DelayQueue<OrderDelay> queue = new DelayQueue<>();

    @PostConstruct
    public void init(){
        List<Order> orders = orderService.listNotPayOrder();
        for (Order o : orders) {
            OrderDelay orderDelay = new OrderDelay(o.getId(), o.getExpireTime().getTime());
            if (o.getExpireTime().after(new Date())) {
                this.addToOrderDelayQueue(orderDelay);
            } else {
                orderService.removeOrder(orderDelay);
            }
        }
        log.info("queue size = {}", queue.size());
        Executors.newSingleThreadExecutor().execute(new OrderExecutionTimer(queue, orderService));
    }

    /**加入延迟消息队列**/
    public boolean addToOrderDelayQueue(OrderDelay orderDelay){
        return queue.add(orderDelay);
    }


    /**从延迟队列中移除**/
    public void removeToOrderDelayQueue(OrderDelay orderDelay){
        queue.remove(orderDelay);
    }
}
