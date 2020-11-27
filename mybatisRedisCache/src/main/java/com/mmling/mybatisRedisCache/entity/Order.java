package com.mmling.mybatisRedisCache.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @author meimengling
 * @version 1.0
 * @date 2020-4-16 14:41
 */
@Data
@ToString
public class Order {

    private Long id;

    /**
     * 金额
     */
    private String userName;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 状态，0为未支付，1为已支付，2为订单失效，3为取消支付
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 过期时间
     */
    private Date expireTime;

    public Date getExpireTime() {
        Calendar newTime = Calendar.getInstance();
        newTime.setTime(createTime);
        newTime.add(Calendar.MINUTE,25);//日期加10秒

        expireTime = newTime.getTime();
        return expireTime;
    }

}
