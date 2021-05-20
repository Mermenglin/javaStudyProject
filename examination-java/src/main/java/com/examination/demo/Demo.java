package com.examination.demo;

/**
 * 给定两个正整数组A和B，返回两个数组中的公共的、长度最长的子数组的长度
 * <p>
 * A：[1,2,3,2,1]
 * B:[3,2,1,4,7]
 * <p>
 * 结果 3
 *
 * @author meimengling
 * @version 1.0
 * @date 2021-4-28 19:03
 */
public class Demo {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 2, 1, 5};
        int[] b = new int[]{1, 3, 2, 1, 4, 7};

        System.out.println(getMaxArry(a, b));
    }

    public static int getMaxArry(int[] a, int[] b) {
        int result = 0;
        int aIndex = 0;
        int bIndex = 0;
        int start = -1;
        boolean flag = true;
        while (aIndex < a.length && bIndex < b.length) {
            if (a[aIndex] == b[bIndex]) {
                if (start == -1) {
                    start = aIndex;
                }
                aIndex++;
                bIndex++;
                continue;
            }
            if (start != -1) {
                result = result > aIndex - start ? result : aIndex - start;
                start = -1;
            }
            if (flag) {
                aIndex++;
            } else {
                bIndex++;
            }
            if (aIndex >= a.length) {
                flag = false;
                aIndex = 0;
            }
        }
        if (start != -1) {
            result = aIndex - start;
            start = -1;
        }

        return result;
    }

}
