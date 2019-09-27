package com.java8.demo;

/**
 * @Author: meimengling
 * @Date: 2019/8/29 14:22
 */
public class DefaultMethodTest implements /*DefaultMethodInterface1,*/ DefaultMethodInterface2 {

    public static void main(String[] args) {
        DefaultMethodTest defaultMethodTest = new DefaultMethodTest();

        defaultMethodTest.printHello();
    }

//    @Override
//    public void printHello() {
//        System.out.println("DefaultMethodTest say hello");
//    }
}
