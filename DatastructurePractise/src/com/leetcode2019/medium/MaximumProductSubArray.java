package com.leetcode2019.medium;

/*
https://leetcode.com/problems/maximum-product-subarray/
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubArray {
    /*
    Recurrence relationship is like maximum subarray question
    For product since two negatives become positive when multiplied; we need to save the maxEndHere and MinEndHere
    if num[i] i  negative
        than
            find max by multiplying it with min[i-1] or itself
            find min by multiplying it with max[i-1] or itself
    else
            find max by multiplying it with max[i-1] or itself
            find min by multiplying it with min[i-1] or itself
     */
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int maxProduct = nums[0];
        int maxEndHere = nums[0];
        int minEndHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // this swapping does the trick for handeling negative values
            if (nums[i] < 0) {
                int temp = maxEndHere;
                maxEndHere = minEndHere;
                minEndHere = temp;
            }
            maxEndHere = Math.max(maxEndHere * nums[i], nums[i]);
            minEndHere = Math.min(minEndHere * nums[i], nums[i]);
            maxProduct = Math.max(maxProduct, maxEndHere);
        }

        return maxProduct;
    }
}
