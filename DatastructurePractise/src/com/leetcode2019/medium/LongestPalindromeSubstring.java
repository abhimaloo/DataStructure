package com.leetcode2019.medium;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromeSubstring {
    public static String longestPalindromicDP(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }

        boolean[][] isPalindrome = new boolean[input.length()][input.length()];
        int startLong = 0;
        int endLong = 0;

        for (int i = 0; i < input.length(); i++) {
            for (int j = i; j >= 0; j--) {
                boolean isEqual = input.charAt(i) == input.charAt(j);
                // single letter palindrome
                if (i == j) {
                    isPalindrome[i][j] = true;
                } else if (i - j == 1 && isEqual) {  // two letter palindrome
                    isPalindrome[i][j] = true;
                } else if (isEqual && isPalindrome[i - 1][j + 1]) { // check if internal pair is palindrome or not
                    isPalindrome[i][j] = true;
                }

                if (isPalindrome[i][j] && i - j > endLong - startLong) {
                    startLong = j;
                    endLong = i;
                }
            }
        }

        return input.substring(startLong, endLong + 1);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromicDP("ccc"));
    }
}
