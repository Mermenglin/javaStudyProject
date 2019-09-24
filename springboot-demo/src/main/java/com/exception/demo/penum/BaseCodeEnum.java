package com.exception.demo.penum;

/**
 * @Author: meimengling
 * @Date: 2019/9/24 16:42
 */
public enum BaseCodeEnum {

    SUCCESS("00000", "请求成功！"),
    ERROR_CODE("99999", "系统错误！");

    private String code;
    private String msg;

    private BaseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
