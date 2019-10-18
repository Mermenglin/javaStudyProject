package com.conver.DTO;

import com.conver.entity.User;

/**
 * @Author: meimengling
 * @Date: 2019/10/18 11:01
 */
public interface DTOConvert<S, T> {

    T convert(S s);


    /**
     * 当某个入参不支持逆向转换时
     */
    default S convertFor(T t) {
        throw new AssertionError("该对象不支持逆向转换");
    }

}
