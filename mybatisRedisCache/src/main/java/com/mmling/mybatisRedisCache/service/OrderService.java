package com.mmling.mybatisRedisCache.service;

import com.mmling.mybatisRedisCache.entity.Order;
import com.mmling.mybatisRedisCache.job.OrderDelay;

import java.util.List;

/**
 * @author meimengling
 * @version 1.0
 * @date 2020-4-16 14:40
 */
public interface OrderService {

    Order getOrderById(Long id);

    void removeOrder(OrderDelay orderDelay);

    void cancelOrder(Long id);

    List<Order> listNotPayOrder();

    Order add(Order order);

    Order select(String userName);
}
