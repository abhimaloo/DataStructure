package com.leetcode2019.hard;

/*
https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.

 */
public class LongestSubstringKDistinct {

    public static int kDistinct(String s, int k) {
        if (s.length() == 0 || k == 0) return 0;
        int left = 0;
        int longest = 0;
        int[] map = new int[256];
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map[s.charAt(i)]++ == 0) {
                num++;
            }
            while (num > k) {
                if (--map[s.charAt(left++)] == 0) {
                    num--;
                }
            }
            longest = Math.max(longest, i - left + 1);
        }

        return longest;
    }


    public static void main(String[] args) {
        System.out.println(kDistinct("abcabcabc", 2));
    }
}
