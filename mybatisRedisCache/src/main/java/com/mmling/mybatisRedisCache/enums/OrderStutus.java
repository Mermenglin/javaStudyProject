package com.mmling.mybatisRedisCache.enums;

/**
 * @author meimengling
 * @version 1.0
 * @date 2020-4-16 15:02
 */
public enum OrderStutus {

    UNPAID(0, "未支付"),
    PAID(1, "已支付"),
    PAYMENTTIMEOUT(2, "支付超时"),
    CANCELPAYMENT(3, "取消支付"),
    ;

    private Integer code;

    private String msg;

    OrderStutus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }
}
