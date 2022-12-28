package com.leetcode2022.blind75.strings;

/*
Given a string s, return the longest
palindromic

substring
 in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String input) {
        if (input == null || input.length() == 0)
            return input;

        boolean[][] isPalindrome = new boolean[input.length()][input.length()];
        int startLongest = 0;
        int endLongest = 0;

        for (int i = 0; i < input.length(); i++) {
            for (int j = i; j >= 0; j--) {
                boolean isEqual = input.charAt(i) == input.charAt(j);
                if (i == j) { // single length strings
                    isPalindrome[i][j] = true;
                } else if (i - j == 1 && isEqual) {
                    isPalindrome[i][j] = true;  // palindrome of length 2
                } else if (isPalindrome[i - 1][j + 1] && isEqual) { // this is for any length palindrome where internal substrings are palindromes
                    isPalindrome[i][j] = true;
                }

                if (isPalindrome[i][j] && i - j > endLongest - startLongest) {
                    startLongest = j;
                    endLongest = i;
                }

            }
        }

        return input.substring(startLongest, endLongest + 1);
    }
}
