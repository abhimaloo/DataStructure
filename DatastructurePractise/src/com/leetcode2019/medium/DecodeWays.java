package com.leetcode2019.medium;

/*
https://leetcode.com/problems/decode-ways/
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class DecodeWays {
    /*
    Elite DP solution.  How to understand the solution
    So the encoding goes like this -
            "1"   -> A  (total ways)  - 1
            "11"   -> (AA,K)  (total ways)  - 2
            "112"  -> (AAB, KB, AL) (total ways)  - 3
            "11123"  -> Here we need to understand the recurrence relationship
            for ex - Any length word can be decoded if  -
            decode[0, n-1] + decode(i) + decode[0, n-2] + decode(n-2, n)
            In simple words in above example - 1112 "3" can be decoded in ways -
            numOf ways  decode 1112 + number of ways to decode "3" (we consider single letter) or
            numOf ways  decode 111 + number of ways to decode "23" (we consider double letter)

            applying that recurrence relationship, final formula is
            ways[n] = ways[n-1] + ways(single character at n) + ways[n -2] + ways(ways of double character at n-1 and n)
      */
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) return 1;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;   // taking care of string starting from 0 is important edge case

        for (int n = 2; n <= s.length(); n++) {
            int singleDigit = Integer.parseInt(s.substring(n - 1, n));
            int doubleDigit = Integer.parseInt(s.substring(n - 2, n));
            if (singleDigit >= 1 && singleDigit <= 9) {
                dp[n] += dp[n - 1];
            }

            if (doubleDigit >= 10 && doubleDigit <= 26) {
                dp[n] += dp[n - 2];
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("27"));
    }
}
