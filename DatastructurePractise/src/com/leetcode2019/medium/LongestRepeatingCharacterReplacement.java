package com.leetcode2019.medium;

/*
https://leetcode.com/problems/longest-repeating-character-replacement/
Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.

In one operation, you can choose any character of the string and change it to any other uppercase English character.

Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.


Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

 */
public class LongestRepeatingCharacterReplacement {
    /*
    Algorithm
    do frequency count
    we will find the letter with ongest
     */
    public int characterReplacement(String s, int k) {
        int[] map = new int[26];
        int maxCount = 0;
        int left = 0;
        int right = 0;
        int maxLength = Integer.MIN_VALUE;

        while (right < s.length()) {
            // keeping the frequency count of maximum appearing character and also how often it has come
            maxCount = Math.max(maxCount, ++map[s.charAt(right) - 'A']);
            // here -> right - left + 1 is length of the sliding window
            // right - left +1 - maxCount is the number of alphabests which are not repeated or requires change
            // right - left +1 - maxCount > k means the numbe rof alphabets which need change are greater than allowed hence shrink the window by increasing left pointer

            if (right - left + 1 - maxCount > k) {
                map[s.charAt(left++) - 'A']--;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }
}
