package com.mmling.mybatisRedisCache.dao;

import com.mmling.mybatisRedisCache.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author meimengling
 * @version 1.0
 * @date 2020-4-16 15:06
 */
@Mapper
public interface OrderMapper {
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);

    List<Order> listPayStutsOrder(@Param("status") Integer status);

    Order getOrderById(@Param("id") Long id);

    void add(Order order);

    Order selectByName(@Param("userName") String userName);
}
