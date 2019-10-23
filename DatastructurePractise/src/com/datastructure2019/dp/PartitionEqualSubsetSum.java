package com.datastructure2019.dp;

/*
https://leetcode.com/problems/partition-equal-subset-sum/
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.


Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].


Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

 */
public class PartitionEqualSubsetSum {
    /*
    This is like 0-1 Knapsack problem. We need to find whether a certain item should be chosen in the bucket for capacity c or not
    DP table would look like this
          0  1  2  3  4  5  6  7  8  9  10  11   X Axis is sum and y axis is array
   None   T  F  F  F  F  F  F  F  F  F  F   F
      1   T  T  F  F  F  F  F  F  F  F  F   F
      5   T  T  F  F  F  T  T  F  F  F  F   F
      11  T  T  F  F  F  T  T  F  F  F  F   T
      5   T  T  F  F  F  T  T  F  F  F  T   T


      DP table is filled by
      DP[i][j] = dp[i-1][j] || dp[i-1][j-num[j]]

      like knapsack at every moment we are testing if we can take this item or not.

     */
    public static boolean canPartition(int[] nums) {
        if (nums.length == 0) return false;
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }

        if (totalSum % 2 != 0) {
            return false;
        }

        totalSum /= 2;  //divide it by two to reach the required half

        boolean[][] dp = new boolean[nums.length + 1][totalSum + 1];
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }


        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= totalSum; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[nums.length][totalSum];

    }

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }

}
