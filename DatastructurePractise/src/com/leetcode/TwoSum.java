package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find two numbers such that they add up to a specific target number.

 The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

 You may assume that each input would have exactly one solution.

 Input: numbers={2, 7, 11, 15}, target=9
 Output: index1=1, index2=2

 https://oj.leetcode.com/problems/two-sum/
 * Created by abhimaloo on 9/7/14.
 */
public class TwoSum {

    public static int[] input = {2,7,11,15};

    public static void twoSum(int[] input, int sum) {
        Map<Integer, Integer> helper = new HashMap<>();
        for(int i = 0; i < input.length; i++) {
            if(helper.containsKey(new Integer(input[i]))) {
                System.out.println(" index 1 :" + helper.get(new Integer(input[i])) + " index 2 :"+ i );
            } else {
                helper.put(new Integer(sum - input[i]), i);
            }
        }

    }


    public static void main(String[] args) {
        twoSum(input, 22);
    }
}
