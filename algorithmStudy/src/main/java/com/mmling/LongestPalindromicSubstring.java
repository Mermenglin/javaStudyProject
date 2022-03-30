package com.mmling;

/**
 * 最长回文子串
 * 输入: "babad"
 * 输出: "bab"
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @author Meimengling
 * @date 2022-3-30 16:46
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbd"));
    }

    private static int index = 0, length = 0;
    public static String longestPalindrome (String s) {
        for (int i=0; i<s.length() - 1; i++) {
            palindrom(s, i, i);
            palindrom(s, i, i+1);
        }

        return s.substring(index, index + length);
    }

    public static void palindrom(String s, int left, int right) {
        while (right < s.length() && left >= 0 && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
        }
        if (length < right - left - 1) {
            // 回文起始位置多减了一次，所以需要加1
            index = left + 1;
            length = right - left - 1;
        }
    }

}
