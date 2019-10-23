package com.leetcode2019.hard;

/*
https://leetcode.com/problems/minimum-window-substring/
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    /*
    The trick is simple
    https://www.youtube.com/watch?v=eS6PZLjoaq8
     */
    public static String minWindow(String s, String t) {

        // get the frequency count
        int[] map = new int[26];
        for (char i : t.toCharArray()) {
            map[i - 'A']++;
        }

        // lets declare two pointers to cover sliding window
        int left = 0;
        int right = 0;
        int minWindow = Integer.MAX_VALUE;
        int minLeft = -1;
        int count = t.length();
        while (right < s.length()) {
            if (map[s.charAt(right++) - 'A']-- > 0) {
                count--;
            }
            // this means condition has been satisfied between left and right
            while (count == 0) {
                if (right - left < minWindow) {
                    minWindow = right - left;
                    minLeft = left;
                }

                if (map[s.charAt(left++) - 'A']++ == 0) {
                    count++;
                }
            }
        }

        return minLeft == -1 ? "" : s.substring(minLeft, minLeft + minWindow);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}
