package com.rabbitmq.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: meimengling
 * @Date: 2019/8/10 17:50
 */

@Data
@ToString
public class User {

    public User() {

    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    private String name;

    private Integer age;
}
