package com.example.demo.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 编写一个函数：
 * <p>
 * 类Solution {public int solution（int [] A）; }
 * <p>
 * 在给定一个由N个整数组成的数组A的情况下，该函数返回在A中不出现的最小的正整数（大于0）。
 * <p>
 * 例如，给定A = [1、3、6、4、1、2]，该函数应返回5。
 * <p>
 * 给定A = [1,2,3]，该函数应返回4。
 * <p>
 * 给定A = [-1，-3]，该函数应返回1。
 * <p>
 * 针对以下假设编写有效的算法：
 * <p>
 * N是在范围[内的整数1 .. 100000 ];
 * 数组A的每个元素都是[[ -1,000,000 .. 1,000,000 ]范围内的整数。
 *
 * @author Meimengling
 * @date 2021-4-22 17:32
 */
public class DemoTEST {
    public static void main(String[] args) {
        DemoTEST d = new DemoTEST();

//        System.out.println(d.solution3(3, 7));
        System.out.println(d.solution4(15958));

    }

    /**
     * S 中仅有 a 和 b
     * 所有的a在b之前，返回true
     * 否则返回false
     *
     * @param S
     * @return
     */
    public boolean solution(String S) {
        // write your code in Java SE 8
        int a = S.lastIndexOf('a');
        int b = S.indexOf('b');
        if (b < 0) {
            return true;
        }
        return a < b;
    }

    public int solution3(int A, int B) {
        // write your code in Java SE 8
        int n = A * B;
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    public int solution4(int N) {
        // write your code in Java SE 8
        List<Integer> list = new ArrayList<>();
        String ns = String.valueOf(N);
        for (int i = 0; i < ns.length(); i++) {
            if (ns.charAt(i) == '5') {
                String result = "0";
                if (i == 0 && i + 1 < ns.length()) {
                    result = ns.substring(i + 1);
                } else if (i + 1 < ns.length() && i > 0) {
                    result = ns.substring(0, i) + ns.substring(i + 1, ns.length());
                } else if (i + 1 == ns.length() && i > 0) {
                    result = ns.substring(0, i);
                }
                list.add(Integer.parseInt(result));
            }
        }
        Collections.sort(list);
        Collections.reverse(list);

        return list.get(0);
    }

}
