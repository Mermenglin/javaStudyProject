package com.mmling.mybatisRedisCache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MybatisRedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisRedisCacheApplication.class, args);
    }

}
