package com.redis.controller;

import com.redis.service.RedisDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: meimengling
 * @Date: 2019/9/25 15:41
 */
@RestController
@RequestMapping("/v1/redisDemo")
@Slf4j
public class RedisDemoController {

    @Autowired
    RedisDemoService redisDemoService;

    @PostMapping("/incr")
    public Long incr(@RequestParam String key) {
        return redisDemoService.incr(key);
    }

    @PostMapping("/get")
    public String get(@RequestParam String key) {
        return redisDemoService.get(key);
    }

}
