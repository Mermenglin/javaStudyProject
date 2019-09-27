package com.java8.demo;

/**
 * @Author: meimengling
 * @Date: 2019/8/29 14:21
 */
public interface DefaultMethodInterface1 {

    default void printHello(){
        System.out.println("DefaultMethodInterface1 say hello");
    }
}
