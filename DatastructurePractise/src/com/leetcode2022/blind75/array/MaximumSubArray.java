package com.leetcode2022.blind75.array;

/*
Given an integer array nums, find the
subarray
 which has the largest sum and return its sum.



Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Example 2:

Input: nums = [1]
Output: 1
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104


Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubArray {
    public static int maxSubArray(int[] input) {
        /*
        Intuition: Keep a running sum; if running sum of a prefix is negative then the run should be reset to 0;
        this means - the max sum substring would not contain a chain which takes the current sum to negative because
        recovering from it would take more positive values ; instead start the sum from the positive value; Always keep
        the max running Sum at every event so that you can return it at the end.
         */
        if (input == null || input.length == 0) {
            return 0;
        }
        int maxSum = input[0];
        int curSum = 0;

        for (int i = 0; i < input.length; i++) {
            if (curSum < 0) {
                curSum = 0;
            }
            curSum += input[i];

            maxSum = Math.max(curSum, maxSum);
        }

        return maxSum;
    }
}
