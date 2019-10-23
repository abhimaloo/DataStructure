package com.leetcode2019.hard;

import java.util.HashSet;

/*
https://leetcode.com/problems/longest-consecutive-sequence/
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet();
        for (int n : nums) {
            set.add(n);
        }
        int sumMax = 0;

        for (int n : nums) {
            if (set.remove(n)) {
                int sum = 1;
                int i = n;
                while (set.remove(i - 1)) {
                    i--;
                    sum++;
                }
                i = n;
                while (set.remove(i + 1)) {
                    i++;
                    sum++;
                }
                sumMax = Math.max(sum, sumMax);
            }
        }

        return sumMax;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{1, 2, 0, 1}));
    }
}
