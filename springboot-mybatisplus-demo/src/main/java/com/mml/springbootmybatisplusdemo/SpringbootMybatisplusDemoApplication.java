package com.mml.springbootmybatisplusdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mml.springbootmybatisplusdemo.dao")
@SpringBootApplication
public class SpringbootMybatisplusDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisplusDemoApplication.class, args);
    }

}
