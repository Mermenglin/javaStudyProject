package com.mml.springbootkafka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: meimengling
 * @Date: 2019/10/18 11:05
 */
@Controller
public class UserController {


    @PostMapping
    public String addUser() {
        System.out.println("hello");
        return "hello";
    }
}
