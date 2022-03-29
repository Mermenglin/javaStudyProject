package com.example.demo;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @Author: meimengling
 * @Date: 2019/8/27 19:01
 */
public class Solution {

    public static int trap(int[] height) {

        if (height == null || height.length < 1)
            return 0;

        int[] leftMax = new int[height.length];
        leftMax[0] = height[0];

        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        int[] rightMax = new int[height.length];
        rightMax[height.length - 1] = height[height.length - 1];

        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        int count = 0;
        for (int i = 0; i < height.length; i++) {
            count += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] a = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
//        System.out.println(trap(a));

        int[] b = {4, 2, 3};
        System.out.println(trap(b));
    }
}