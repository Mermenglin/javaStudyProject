package com.exception.demo.entity;

import com.exception.demo.penum.BaseCodeEnum;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: meimengling
 * @Date: 2019/9/24 16:08
 */
@Data
@ToString
public class BaseResult<T> {

    private String code;
    private String msg;
    private T data;

    public BaseResult() {
        
    }

    public BaseResult(T data) {
        this.code = BaseCodeEnum.SUCCESS.getCode();
        this.msg = BaseCodeEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public BaseResult(T data, String code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(BaseCodeEnum baseErrorCodeEnum) {
        this.code = baseErrorCodeEnum.getCode();
        this.msg = baseErrorCodeEnum.getMsg();
    }

}
