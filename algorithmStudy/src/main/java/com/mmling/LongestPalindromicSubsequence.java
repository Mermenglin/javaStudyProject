package com.mmling;

/**
 * 最长回文子序列
 * 最长回文子序列和上一题最长回文子串的区别是，子串是字符串中连续的一个序列，而子序列是字符串中保持相对位置的字符序列，例如，"bbbb"可以是字符串"bbbab"的子序列但不是子串。
 * <p>
 * 输入:
 * "bbbab"
 * 输出:
 * 4
 * <p>
 * 动态规划，
 * 相等时 每次计算都根据左下角的数据进行加2计算
 * 不等时 每次根据下边或者左边取最大值
 * |      | b    | b    | b    | a    | b    |
 * | ---- | ---- | ---- | ---- | ---- | ---- |
 * | b    | 1    | 2    | 3    | 3    | 4    |
 * | b    |      | 1    | 2    | 2    | 3    |
 * | b    |      |      | 1    | 1    | 3    |
 * | a    |      |      |      | 1    | 1    |
 * | b    |      |      |      |      | 1    |
 *
 * @author Meimengling
 * @date 2022-3-30 17:00
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        System.out.println(palindromicSubseq("bbbab"));
    }

    public static int palindromicSubseq(String s) {
        int len = s.length();
        int[][] temp = new int[len][len];


        for (int i = 0; i < len; i++) {
            temp[i][i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    temp[i][j] = temp[i - 1][j + 1] + 2;
                } else {
                    temp[i][j] = Math.max(temp[i][j + 1], temp[i - 1][j]);
                }
            }
        }

        return temp[len - 1][0];
    }

}
