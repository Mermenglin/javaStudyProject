package com.example.demo;

import org.junit.Test;

import java.util.Random;

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

    @Test
    public void  test(){
        Random random = new Random();
        int i1 = random.nextInt(2);
        String conn = null;
        switch (i1){
            case 0:
                conn = "realUrl0";
                break;
            case 1:
                conn = "realUrl";
                break;
            default:
                conn = "realURL2";
        }
        System.out.println(conn);
    }

}
