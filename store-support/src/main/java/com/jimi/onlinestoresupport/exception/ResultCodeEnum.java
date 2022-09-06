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


package com.jimi.onlinestoresupport.exception;

/**
 * 自定义返回码
 *
 * @author Mei.Mengling
 * @date 2022-09-06
 * @since 1.0.0
 */
public enum ResultCodeEnum {

    /**
     * 操作成功
     */
    SUCCESS(0, "Success"),
    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION(10000, "SystemException"),
    /**
     * 非法参数
     */
    ILLEGAL_PARAMETER(10001, "IllegalParameter"),
    ;

    private int code;

    private String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultCodeEnum of(int code) {
        ResultCodeEnum result = null;
        for (ResultCodeEnum value : ResultCodeEnum.values()) {
            if (value.code == code) {
                result = value;
                break;
            }
        }
        return result;
    }

    public static boolean isSuccess(int code) {
        return code == SUCCESS.code;
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
}
