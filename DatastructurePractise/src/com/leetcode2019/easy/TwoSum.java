package com.leetcode2019.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * <p>
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */

public class TwoSum {
    public int[] twoSum(int[] in, int sum) {
        Map<Integer, Integer> mask = new HashMap<>();
        int[] response = new int[2];
        for (int i = 0; i < in.length; i++) {
            if (mask.containsKey(in[i])) {
                response[0] = mask.get(in[i]);
                response[1] = i;
                return response;
            } else {
                mask.put(sum - in[i], i);
            }
        }

        return response;
    }

    public static void main(String[] args) {

    }
}
