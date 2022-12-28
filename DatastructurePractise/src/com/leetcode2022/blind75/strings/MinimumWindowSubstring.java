package com.leetcode2022.blind75.strings;

/*
Given two strings s and t of lengths m and n respectively, return the minimum window
substring
 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.


Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        int left = 0;
        int right = 0;
        char[] sa = s.toCharArray();
        int[] map = new int[128]; // hold all ascii chars
        int minLength = Integer.MAX_VALUE;
        int minLeft = -1;
        int count = t.length();

        // fill map with t freq count
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        }

        while (right < s.length()) {
            if (map[sa[right++]]-- > 0) {
                count--;
            }

            while (count == 0) {
                if (right - left < minLength) {
                    minLength = right - left;
                    minLeft = left;
                }

                if (map[sa[left++]]++ == 0) {
                    count++;
                }
            }
        }

        return minLeft == -1 ? "" : s.substring(minLeft, minLeft + minLength);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

}
