package com.leetcode2019.medium;

/*
https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.



Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.


Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 */
public class PartitionKEqualSubset {
    /*
    Solution entails backtracking and memoizing the different results of index, partition number and currSum and
     */

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return false;
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }

        if (totalSum % k != 0) return false;

        totalSum /= k;

        return canPartition(0, nums, k, 0, totalSum, new boolean[nums.length]);
    }

    public static boolean canPartition(int index, int[] nums, int partition, int currSum, int targetSum, boolean[] visited) {
        if (partition == 1) return true;

        if (currSum > targetSum || index > nums.length - 1) {
            return false;
        }

        if (currSum == targetSum) {
            return canPartition(0, nums, partition - 1, 0, targetSum, visited);
        }

        for (int i = index; i < nums.length; i++) {
            if (!visited[i] && currSum + nums[i] <= targetSum) {
                visited[i] = true;
                if (canPartition(i + 1, nums, partition, currSum + nums[i], targetSum, visited)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println(canPartitionKSubsets(nums, k));
    }
}
