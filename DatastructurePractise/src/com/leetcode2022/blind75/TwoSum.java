package com.leetcode2022.blind75;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]


Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.
 */
public class TwoSum {
    public static int[] twoSum(int[] num, int target) {
        if (num == null || num.length == 0)
            return null;

        Map<Integer, Integer> diffMap = new HashMap<>(num.length);

        // put all the diffs in a map
        for (int i = 0; i < num.length; i++) {
            if (diffMap.containsKey(num[i])) {
                return new int[]{diffMap.get(num[i]), i};
            }
            diffMap.put(target - num[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] input = new int[]{2, 7, 11, 15};
        int[] res = twoSum(input, 9);
        System.out.println(res[0] + " " + res[1]);
    }
}
