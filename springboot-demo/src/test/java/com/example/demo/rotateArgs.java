package com.example.demo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组测试
 *
 * @Author: Robert_mml
 * @Version 1.0 2019/11/12
 */
public class rotateArgs {

    /**
     * 可以将一个二维数组顺时针旋转90度
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] =
                        matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] =
                        matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] =
                        matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    public void print2DArg(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            printArg(matrix[i]);
            System.out.println();
        }
    }

    public void printArg(int[] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + "\t");
        }
    }

    @Test
    public void rotate() {
        int[][] matrix = {{1, 1, 3, 4, 5}, {2, 2, 4, 5, 6}, {3, 3, 5, 6, 7}, {4, 4, 6, 7, 8}, {5, 5, 7, 8, 9}};
        print2DArg(matrix);
        rotate(matrix);
        System.out.println("=====");
        print2DArg(matrix);
    }

    /**
     * 一个数组，除一个元素外其它都是两两相等，求那个元素?
     */
    public int findOneFromArg(int[] a) {
        int len = a.length, res = 0;
        for (int i = 0; i < len; i++) {
            res = res ^ a[i];
        }
        return res;
    }

    @Test
    public void findOneFromArg() {
        int[] a = {1, 2, 3, 4, 5, 4, 3, 5, 1};
        System.out.println(findOneFromArg(a));
    }

    /**
     * 找出数组中和为sum的一对组合，找出一组就行
     */
    public int[] twoSum(int[] a, int sum) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(sum - a[i])) {
                result[0] = map.get(sum - a[i]);
                result[1] = i;
            } else {
                map.put(a[i], i);
            }
        }
        return result;
    }

    @Test
    public void twoSum() {
        int[] a = {2, 2, 3, 4, 5};
        printArg(twoSum(a, 9));
    }

    /**
     * 求一个数组中连续子向量的最大和
     */
    public int maxSubArray(int[] a) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        if (a == null || a.length == 0) {
            return sum;
        }
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            maxSum = Math.max(maxSum, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    @Test
    public void maxSubArray() {
        int[] a = {2, -2, -3, 4, -5};
        System.out.println(maxSubArray(a));
    }

    @Test
    public void findKthLargest() {
        int[] a = {2, 3, 4, 10, 6, 7, 5, 8, 9};
        System.out.println(findKthLargest(a, 1));
    }

    /**
     * 寻找一数组中前K个最大的数
     */
    public int findKthLargest(int[] nums, int k) {
        if ((k < 1 || nums == null) && k <= nums.length) {
            return 0;
        }
//        return getKth(nums.length - k + 1, nums, 0, nums.length - 1);
        return getKth(k, nums, 0, nums.length - 1);
    }

    // 根据快速排序实现
    public int getKth(int k, int[] nums, int start, int end) {

        int pivot = nums[end];
        if (start == end) {
            return nums[nums.length - k];
        }

        int left = start;
        int right = end;

        while (true) {

            while (nums[left] < pivot && left < right) {
                left++;
            }

            while (nums[right] >= pivot && right > left) {
                right--;
            }

            if (left == right) {
                break;
            }
            // 将小的移到左边，大的移到右边
            swap(nums, left, right);
        }

        swap(nums, left, end);

//        if (k == left + 1) {
        if (k == nums.length - right) {
            return pivot;
        } else if (k < left + 1) {
            return getKth(k, nums, start, left - 1);
        } else {
            return getKth(k, nums, left + 1, end);
        }
    }

    public void swap(int[] nums, int n1, int n2) {
        int tmp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = tmp;
    }
}
