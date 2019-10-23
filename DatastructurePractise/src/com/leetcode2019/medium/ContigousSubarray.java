package com.leetcode2019.medium;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/contiguous-array/
 */
public class ContigousSubarray {
    public int findMaxLength(int[] nums) {
        int numZeros = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1);
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                numZeros++;
            } else {
                numZeros--;
            }

            if (sumMap.containsKey(numZeros)) {
                maxLength = Math.max(maxLength, i - sumMap.get(numZeros));
            } else {
                sumMap.put(numZeros, i);
            }
        }

        return maxLength;
    }
}
