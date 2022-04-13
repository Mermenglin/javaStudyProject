package com.mmling;

/**
 * 理解KMP算法：
 * http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
 * <p>
 * 代码参考：
 * https://blog.sengxian.com/algorithms/kmp
 *
 * @author Meimengling
 * @date 2022-3-29 15:09
 */
public class KMPStr {

    public static void main(String[] args) {
        String s = "CABABACABABC";
        String match = "ABABC";

//        process(match.toCharArray());
        findStr(s, match);
    }

    private static int findStr(String s, String match) {
        int[] PartialMatchTable = process(match.toCharArray());
        int i, j = 0;
        char[] schar = s.toCharArray();
        char[] mchar = match.toCharArray();
        // 待匹配串循环
        for (i = 0; i < schar.length; i++) {
            // 如果不相同，匹配串往前回溯，直到与原串字符相同或回溯到第一位
            while (j != 0 && schar[i] != mchar[j]) {
                j = PartialMatchTable[j - 1];
            }
            // 字符相同，匹配串游标往后移动
            if (schar[i] == mchar[j]) {
                j++;
            }
            // 移到到匹配串的最后一位，找到完全相同的部分
            if (j == match.length()) {
                System.out.println("相同的位置在第" + (i - j + 2) + "位; i=" + i + "; j=" + j);
                j = PartialMatchTable[j];
            }
        }
        return 0;
    }

    // 最长前缀后缀，算出模板中相同部分长度，用于不匹配回溯
    private static int[] process(char[] chars) {
        String temp = null;
        int[] PartialMatchTable = new int[chars.length + 1];
        int j = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[j] == chars[i]) {
                PartialMatchTable[i] = PartialMatchTable[i - 1] + 1;
                j++;
            } else {
                j = 0;
            }
        }

        return PartialMatchTable;
    }

}
