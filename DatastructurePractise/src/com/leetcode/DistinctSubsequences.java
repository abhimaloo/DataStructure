package com.leetcode;

/**
 *
 * Given a string S and a string T, count the number of distinct subsequences of T in S.

 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

 Here is an example:
 S = "rabbbit", T = "rabbit"

 Return 3.

 https://oj.leetcode.com/problems/distinct-subsequences/
 * Created by abhimaloo on 8/27/14.
 */
public class DistinctSubsequences {
    public static String s = "rabbbit";
    public static String t = "rabbit";

    /**
     * trick is to apply DP -
     * maintain a 2d array numOfSubseq[i][j] which represents number of subsequences like t(o ..j) which can be made form s(0..i)
     * if ith element of s matches then number of substrings made by i to j will be = numOfSubstring[i-1][j-1] + numOfSubstring[i-1][j]
     *
     * Theory is simple either an element in S will take part in creation of substring or it will not.
     * It can take part if it has a match ..It can also not take part if its a match because it can rely on ither matches
     * if its not a match .. then the number of sibstringhs will be equivalent to number of substring found without it.
     *
     * @param s
     * @param t
     * @return
     */
    public static int subsequences(String s, String t) {
        int[][] numOfSubseq = new int[s.length()+1][t.length()+1];

        for( int i = 0; i < s.length(); i++) {
            numOfSubseq[i][0] = 1;
        }

        for( int i = 1; i <= s.length(); i++) {
            for( int j =1; j <= t.length() ; j++) {
                // if character matches .. it could or could not take part in substring
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    // add the number of subsequence which can be made when it takes part + when it decides not to take part
                    numOfSubseq[i][j] = numOfSubseq[i-1][j-1] + numOfSubseq[i-1][j];
                } else {
                    // since last char does not match .. number of subsequence will be equals to it not taking part
                    numOfSubseq[i][j] = numOfSubseq[i-1][j];
                }
            }
        }

        return numOfSubseq[s.length()][t.length()];
    }

    public static void main(String[] args) {
        System.out.println(subsequences(s, t));

    }

}
