package com.java8.demo;

/**
 * @Author: meimengling
 * @Date: 2019/8/29 14:21
 */
public interface DefaultMethodInterface2 {

    default void printHello() {
        System.out.println("DefaultMethodInterface2 say hello!");
    }

    static void printWhy() {
        System.out.println("DefaultMethodInterface2 say why?");
    }
}
