package com.mmling.mybatisRedisCache.service.impl;

import com.mmling.mybatisRedisCache.dao.OrderMapper;
import com.mmling.mybatisRedisCache.entity.Order;
import com.mmling.mybatisRedisCache.enums.OrderStutus;
import com.mmling.mybatisRedisCache.job.DelayOrderComponent;
import com.mmling.mybatisRedisCache.job.OrderDelay;
import com.mmling.mybatisRedisCache.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author meimengling
 * @version 1.0
 * @date 2020-4-16 14:39
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    DelayOrderComponent delayOrderComponent;

    @Override
    public Order getOrderById(Long id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public void removeOrder(OrderDelay orderDelay) {
        Order order = getOrderById(orderDelay.getId());
        if (order != null && OrderStutus.UNPAID.getCode().equals(order.getStatus())) {
            log.info("订单支付超时，order = {}", order);
            orderMapper.updateStatus(order.getId(), OrderStutus.PAYMENTTIMEOUT.getCode());
        }
        if (delayOrderComponent != null) {
            //第一次加载，delayOrderComponent还未注入
            delayOrderComponent.removeToOrderDelayQueue(orderDelay);
        }
    }

    @Override
    public void cancelOrder(Long id){
        Order order = getOrderById(id);
        OrderDelay orderDelay = new OrderDelay(id);
        if (order != null && OrderStutus.UNPAID.getCode().equals(order.getStatus())) {
            log.info("订单取消支付，order = {}", order);
            orderMapper.updateStatus(id, OrderStutus.CANCELPAYMENT.getCode());
            delayOrderComponent.removeToOrderDelayQueue(orderDelay);
        }
    }

    @Override
    public List<Order> listNotPayOrder() {
        return orderMapper.listPayStutsOrder(OrderStutus.UNPAID.getCode());
    }

    @Override
    public Order add(Order order) {
        orderMapper.add(order);
        order = orderMapper.getOrderById(order.getId());
        OrderDelay orderDelay = new OrderDelay(order.getId(),order.getExpireTime().getTime());
        delayOrderComponent.addToOrderDelayQueue(orderDelay);
        log.info("新增订单，order = {}", order);
        return order;
    }

    @Override
    public Order select(String userName) {
        return orderMapper.selectByName(userName);
    }
}
