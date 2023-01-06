package com.leetcode2022.blind75.dp;

import java.util.Arrays;

/*
Given an integer array nums, return the length of the longest strictly increasing
subsequence
.



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104


Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] lis = new int[nums.length];
        Arrays.fill(lis, 1);
        int maxLength = 1;
        lis[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[j] + 1, lis[i]);
                    maxLength = Math.max(maxLength, lis[i]);
                }
            }
        }

        return maxLength;
    }
}
