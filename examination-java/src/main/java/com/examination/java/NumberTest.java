package com.examination.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meimengling
 * @version 1.0
 * @date 2021-4-26 11:05
 */
public class NumberTest {

    public static void main(String[] args) {
        int a = 76;
        boolean flag = a > 0;

        if (flag == false) {
            a = a * -1;
        }
        List<Integer> list = new ArrayList<>();

        while (true) {
            list.add(a%10);
            a=a/10;//降位
            if (a == 0) {
                break;
            }
        }
        int i = list.size();
        for (; i > 0; i--) {
            if (list.get(i - 1) > 5 == flag) {
                continue;
            } else {
                break;
            }
        }
        int result = 0;
        for (int j = list.size() -1 ; j >= 0; j--) {
            if (i == list.size() && j == list.size() - 1) {
                result = 5;
            }
            result = result * 10 + list.get(j);
            if (j == i) {
                result = result * 10 + 5;
            }
        }
        if (!flag) {
            result *= -1;
        }
        System.out.println(result);

    }

}
