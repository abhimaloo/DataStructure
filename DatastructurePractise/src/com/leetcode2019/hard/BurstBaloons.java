package com.leetcode2019.hard;

/*
https://leetcode.com/problems/burst-balloons/
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBaloons {
    /*
    Solution - https://www.youtube.com/watch?v=IFNibRVgFBo
    Trick is to apply DP in bottom up fashion. We need to find
    the max coing we could get for different length (subarrays) and which ballon to burst last for maximum gain
     */

    public static int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        return maxCoins(nums, 0, nums.length - 1, dp);
    }

    public static int maxCoins(int[] nums, int left, int right, int[][] dp) {
        if (left > right) {
            return 0;
        }

        if (dp[left][right] != 0) {
            return dp[left][right];
        }
        int maxValue = 0;
        for (int i = left; i <= right; i++) {
            int value = maxCoins(nums, left, i - 1, dp) + maxCoins(nums, i + 1, right, dp) +
                    (getNum(nums, left - 1) * getNum(nums, i) * getNum(nums, right + 1));
            maxValue = Math.max(maxValue, value);
        }

        dp[left][right] = maxValue;
        return maxValue;
    }

    public static int getNum(int[] nums, int index) {
        if (index == -1 || index == nums.length) {
            return 1;
        } else {
            return nums[index];
        }

    }

    public static void main(String[] args) {
        int[] baloon = new int[]{3, 1, 5, 8};
        System.out.println(maxCoins(baloon));
    }


}
