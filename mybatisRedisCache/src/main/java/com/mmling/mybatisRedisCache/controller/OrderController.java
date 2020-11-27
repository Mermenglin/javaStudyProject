package com.mmling.mybatisRedisCache.controller;

import com.mmling.mybatisRedisCache.entity.Order;
import com.mmling.mybatisRedisCache.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author meimengling
 * @version 1.0
 * @date 2020-4-16 18:36
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

//    @GetMapping("selectOne")
//    public Student selectOne(Integer id) {
//        return this.orderService.queryById(id);
//    }
//
//    @PostMapping("update")
//    public int updateStudent(@RequestBody Student student){
//        return orderService.update(student).getId();
//    }

    @PostMapping("add")
    public Long insertStudent(@RequestBody Order order) {
        return orderService.add(order).getId();
    }

    @PostMapping("cancelOrder")
    public Boolean cancelOrder(Long id) {
        orderService.cancelOrder(id);
        return true;
    }

    @PostMapping("select")
    public Order select(String userName) {
        return orderService.select(userName);
    }
}
