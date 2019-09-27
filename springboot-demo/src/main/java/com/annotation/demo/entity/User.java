package com.annotation.demo.entity;

import com.annotation.demo.annotation.Constant;

/**
 * @Author: meimengling
 * @Date: 2019/9/27 16:23
 */
public class User {

    @Constant(message = "verson只能为1.0",value="1.0")
    String version;

}
