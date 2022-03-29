package com.example.demo;

import org.junit.Test;

/**
 * @Author: meimengling
 * @Date: 2019/10/10 15:44
 */
public class IntegerTest {

    @Test
    public void nullAddNum() {
//        Integer i = null;       // throw java.lang.NullPointerException
        Integer i = 0;
        Integer j = 10;
        System.out.println(j + i);
    }

}
