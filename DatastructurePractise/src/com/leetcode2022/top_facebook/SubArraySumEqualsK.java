package com.leetcode2022.top_facebook;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2


Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
 */
public class SubArraySumEqualsK {
    /*
    Another tricky Intuition: https://www.youtube.com/watch?v=fFVZt-6sgyo
    Here we will maintain a hashmap of <prefixSum, count> : cumulative sum till a certain index and count (how many times we have seen this cumulative sum)
    idea is simple - if cumulative sum at a certain index is l; lets say l - k(target) = m; now how many prefixes do we have which has m sum; so that we can chop it off to arrive at the target

    */
    public static int subarraySum(int[] nums, int k) {
        int result = 0;
        int sum = 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        // this is to handle the edge case that we have atleast one count of prefixSUm which has prefixSum of 0
        prefixSum.put(0, 1);

        for (int num : nums) {
            sum += num;
            int diff = sum - k;
            // find if the diff is already in the map
            result += prefixSum.getOrDefault(diff, 0);
            prefixSum.put(sum, 1 + prefixSum.getOrDefault(sum, 0));
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1}, 1));
    }
}
