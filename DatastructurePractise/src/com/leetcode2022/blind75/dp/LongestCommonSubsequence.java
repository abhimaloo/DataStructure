package com.leetcode2022.blind75.dp;

/*
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.



Example 1:

Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.


Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 */
public class LongestCommonSubsequence {
    public static int longestCommonSubsequence(String text1, String text2) {
        // keep a table for length m and n where i,j denotes the longest common substring between 0..-i-1 of text1 and 0 to j-1 of text2
        int lcs[][] = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                int text1Cursor = i - 1;
                int text2Cursor = j - 1;

                if (text1.charAt(text1Cursor) == text2.charAt(text2Cursor)) {
                    lcs[i][j] = 1 + lcs[i - 1][j - 1];
                } else {
                    lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j]);
                }
            }
        }

        return lcs[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("ABCDGH", "AEDFHR"));
    }
}
