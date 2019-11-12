package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Robert_mml
 * @Version 1.0 2019/11/1
 */
public class StringUtils {

    public static void main(String[] args) {
        String loanType = "";
        String[] loanTypes = loanType.split(",");
        List<String> loanTypeList = new ArrayList<String>(Arrays.asList(loanTypes));
        System.out.println(loanTypeList);
    }

//    public static void main(String[] args) {
//        String a = "AD428C93DE";
//
//        byte[] bytes = new byte[6];
//
//        StringBuilder sb = new StringBuilder();
//
//        String[] split = a.split("");
//        for (int i = 0; i < split.length; i++) {
//            sb.append(split[i]);
//            if (i % 2 != 0) {
//                int str2HexInt = str2HexInt(sb.toString());
//                bytes[i / 2] = (byte) str2HexInt;
//                sb.delete(0, 2);
//            }
//        }
//
//        for(byte b : bytes) {
//            if (b > 0) {
//                System.out.println(b);
//            } else {
//                System.out.println(256 + b);
//            }
//        }
//
//    }


    static final char[] chars = "0123456789ABCDEF".toCharArray();
    public static int str2HexInt(String str){
        char[] chars1 = str.toCharArray();
        int result = 0;
        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (chars1[i] == chars[j]) {
                    result = (result << 4) | j;
                }
            }
        }
        return result;
    }

    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }
}
