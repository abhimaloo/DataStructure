package com.leetcode2019.medium;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/continuous-subarray-sum/
Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.



Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.


Note:

The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class ContinousSubarraySum {
    /*
    This is a tricky question.
    You need to do 2 things -
    a) maintain the sum and then keep modding it by k and save it in hashmap with the index you are on.
    mod is sort of keeping the distance from multiples of k for you. hence once you find a element which is going to fill that distance
    you knw that you have achieved the result
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length == 0) return false;
        if (nums.length == 1 && nums[0] == k) return false;
        // here k would be the num %k
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);  // put 0 for success criteria
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // add the sum
            if (sum == 0 && i >= 1) return true;

            if (k != 0) {
                sum %= k;
            }

            if (map.containsKey(sum) && i - map.get(sum) > 1) {
                return true;
            } else {
                map.put(sum, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        checkSubarraySum(new int[]{0, 1, 0}, 0);
    }
}
