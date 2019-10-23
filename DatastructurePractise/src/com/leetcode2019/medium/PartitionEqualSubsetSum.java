package com.leetcode2019.medium;

import java.util.HashMap;
import java.util.Map;

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

 */
public class PartitionEqualSubsetSum {
    public static boolean canPartition(int[] nums) {
        if (nums.length == 0) return false;
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }
        // cannot partition it into two parts if the sum is odd
        if (totalSum % 2 != 0) {
            return false;
        }

        return canPartition(0, nums, totalSum, 0, new HashMap<>());

    }

    public static boolean canPartition(int index, int[] nums, int totalSum, int currSum, Map<String, Boolean> state) {
        // return the memoized result
        String curr = index + " " + currSum;
        if (state.containsKey(curr)) {
            return state.get(curr);
        }

        if (currSum == totalSum / 2) {
            return true;
        }

        if (currSum > totalSum / 2 || index == nums.length) {
            return false;
        }

        boolean found = canPartition(index + 1, nums, totalSum, currSum, state)
                || canPartition(index + 1, nums, totalSum, currSum + nums[index], state);
        state.put(curr, found);
        return found;
    }
}
