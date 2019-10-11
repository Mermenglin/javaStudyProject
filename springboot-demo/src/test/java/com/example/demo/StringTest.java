package com.example.demo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: meimengling
 * @Date: 2019/5/10 10:08
 */
public class StringTest {

    public static void main(String[] args) {
        int a, b, sum;
        a = 123;
        b = 456;
        sum = a + b;
        System.out.printf("sum is %d\n", sum);
        return;
    }

    @Test
    public void string2Integer(){
        String i = "70";
        System.out.println(Integer.valueOf(i));

        String s = "70.50";
        System.out.println(Float.valueOf(s));
        System.out.println(Double.valueOf(s));
//        System.out.println(Integer.valueOf(s));
        Float aFloat = Float.valueOf(s);
        Integer a = aFloat.intValue();
        System.out.println(a);
    }

    @Test
    public void test1(){
        String link = "http://webtest.51xcm.cn/xinchao/index.html?c=XAZkV92";
        System.out.println(link.substring(link.lastIndexOf("?c=") + 3));


        String statisticsTime = "2019-10-01";
        statisticsTime = statisticsTime.substring(0, statisticsTime.lastIndexOf("-"));

        System.out.println(statisticsTime);
    }

    @Test
    public void test2(){
        String test = null;
        StringBuffer sb = new StringBuffer("123");

        sb.append(test);
        System.out.println(sb);
    }

    @Test
    public void test3(){
        System.out.println(longestSubstring("aacbbbdc", 2));
    }

    public int longestSubstring(String s, int k) {
        if(k < 2) return s.length();
        return sub(s, k);
    }

    boolean flag = false;

    public int sub(String s, int k){
        if(s.length() < k)
            return 0;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = getMap(chars);
        int temp = 0;
        int start = 0;
        int end = 0;
        for(int i = 0; i < s.length(); ){
            if(map.get(chars[i]) >= k){
                int j =  i;
                for(; j < s.length(); j ++ ){
                    if(map.get(chars[j]) < k)
                        break;
                }
                if(j - i >= k && j - i > temp){
                    temp = j - i;
                    start = i;
                    end = j;
                }
                i = j;
            }
            else {
                i ++;
            }
        }
        if (temp == s.length() || temp == 0)
            return temp;

        int result = sub(s.substring(start, end), k);
        if (flag || result == temp) {
            flag = true;
            return result;
        }
        else
            return 0;
    }

    public Map getMap(char[] chars){
        Map<Character, Integer> map = new HashMap();
        for(int i = 0; i < chars.length; i ++){
            Integer integer = map.get(chars[i]);
            if (integer != null)
                map.put(chars[i], integer + 1);
            else
                map.put(chars[i], 1);
        }
        return map;
    }

    private int process(char[] s, int k, int lo, int hi) {
        if (hi - lo + 1 < k) return 0;

        int[] cnts = new int[26];
        for (int i = lo; i <= hi; ++i) cnts[s[i]-'a']++;

        while (hi - lo + 1 >= k && cnts[s[lo]-'a'] < k) lo++;
        while (hi - lo + 1 >= k && cnts[s[hi]-'a'] < k) hi--;
        if (hi - lo + 1 < k) return 0;

        for (int i = lo; i <= hi; ++i)
            if (cnts[s[i]-'a'] < k) return Math.max(process(s, k, lo, i - 1), process(s, k, i + 1, hi));

        return hi - lo + 1;
    }
}
