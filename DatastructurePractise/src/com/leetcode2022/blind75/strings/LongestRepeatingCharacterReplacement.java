package com.leetcode2022.blind75.strings;

/*
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.



Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.


Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length
 */
public class LongestRepeatingCharacterReplacement {

    /*
    Intuition: Sliding window problem
    we need to keep a frequency count of each character.
     */

    public static int characterReplacement(String s, int k) {
        int maxLength = 0;
        int start = 0;
        int end = 0;
        int maxCount = 0;
        int[] charMap = new int[26];

        for (; end < s.length(); end++) {
            // update the frequency count
            charMap[s.charAt(end) - 'A']++;
            // here -> right - left + 1 is length of the sliding window
            // right - left +1 - maxCount is the number of alphabets which are not repeated or requires change
            // right - left +1 - maxCount > k means the number of alphabets which need change are greater than allowed hence shrink the window by increasing left pointer
            maxCount = Math.max(maxCount, charMap[s.charAt(end) - 'A']);
            if (end - start + 1 - maxCount > k) {
                charMap[s.charAt(start++) - 'A']--;
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;

    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABBB", 2));
    }

}
