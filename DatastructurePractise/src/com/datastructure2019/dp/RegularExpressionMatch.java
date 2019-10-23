package com.datastructure2019.dp;

/*
https://leetcode.com/problems/regular-expression-matching/
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
 */
public class RegularExpressionMatch {
    /*
    trick is to solve it using DP
    we will have a two dimensional metric called boolean dp[string+1][pattern+1].
    Table would looks like this
         ""   X  A  *  B  .  C
      ""  T   F  F  F  F  F  F
      X   F   T  F  T  F  F  F
      A   F
      A   F
      B   F
      Y   F
      C   F

      The rules are - if str(i-1) == pattern(j-1) or str(i-1) == '.' [character matches)
      dp[i][j] = dp[i-1][j-1]   => this is equivalent to plucking ith and jth character from both the strings

      if pattern(j-1) == '*'
        dp[i][j] = Two cases, 1) zero occurence of the letter before "*" hence copy the value of dp[i][j-2]
        if dp[i][j] is still false
        case 2) check if character previous to * matches in the string or to a '.'. If yes - copy dp[i-1][j]
        dp[i-1][j] means consider and remove the matching character from string and compare whether rest of the prefix of string is still mathching to pattern or not
        else :
        dp[i][j] = false
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        dp[0][0] = true;  //empty string matches empty pattern

        //handle patterns like a*, a*b* or a*b*c* ; here for ex - in a* there is not character before a hence we need to
        // take care of that
        for (int i = 1; i < dp[0].length; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        // now build the main table
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {  // if characters match or '.'  remove both of them from s and p and copy the state from prefix without them
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];  // case where there is 0 occurence of element prior to *
                    if (!dp[i][j]) {    // dp [i][j-2] was false then try matching the second case
                        if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {   // check if character before * matches or not
                            dp[i][j] = dp[i - 1][j];
                        }
                    }

                } else {
                    dp[i][j] = false;   // third case if the character does not match mark it false
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        RegularExpressionMatch obj = new RegularExpressionMatch();
        System.out.println(obj.isMatch("aa", "a*"));
    }
}
