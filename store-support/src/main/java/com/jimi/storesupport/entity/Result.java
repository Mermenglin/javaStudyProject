/*
 * COPYRIGHT. ShenZhen JiMi Technology Co., Ltd. 2022.
 * ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
 * on any form or by any means, electronic, mechanical, photocopying, recording,
 * or otherwise, without the prior written permission of ShenZhen JiMi Network Technology Co., Ltd.
 *
 * Amendment History:
 *
 * Date                   By              Description
 * -------------------    -----------     -------------------------------------------
 * 2022-09-06             Mei.Mengling   Create the class
 * http://www.jimilab.com/
 */


package com.jimi.onlinestoresupport.entity;

import com.jimi.onlinestoresupport.exception.ResultCodeEnum;

/**
 * 统一返回类型
 *
 * @author Mei.Mengling
 * @date 2022-09-06
 * @since 1.0.0
 */
public class Result {

    /**
     * 返回的数据包
     */
    private Object data;
    /**
     * 业务码
     */
    private int code = ResultCodeEnum.SUCCESS.getCode();
    /**
     * 消息
     */
    private String msg;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Object data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static Result success(Object data) {
        return new Result(data, ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg());
    }

    public static Result failed() {
        return new Result(null, ResultCodeEnum.SYSTEM_EXCEPTION.getCode(), ResultCodeEnum.SYSTEM_EXCEPTION.getMsg());
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result convert(ResultCodeEnum result) {
        setCode(result.getCode());
        setMsg(result.getMsg());
        return this;
    }
}
