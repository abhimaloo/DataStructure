package com.revision;

/**
        * Given two sequences, find the length of longest subsequence present in both of them
        * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
        * Created by abhishekm787 on 7/17/14.
        */
public class LongestCommonSubsequence {
    public static String s1 = "ABCDGH";
    public static String s2 = "AEDFHR";

    public static int findLongestCommonSubsequence(String s1, String s2) {
        int [][] lcs = new int[s1.length()+1][s2.length()+1];

        //setting the base case, matching anything with empty string will result into 0 length common subsequence
        for( int i=0; i<= s1.length(); i++) {
            lcs[i][0] = 0;

        }
        //setting the base case, matching anything with empty string will result into 0 length common subsequence
        for( int j = 0; j<= s2.length(); j++){
            lcs[0][j] = 0;
        }

        // going through all i's and j's
        for(int i =1; i<=s1.length(); i++) {
            for( int j = 1; j <= s2.length(); j++) {
                //if i and jth character matches.. add one to the length of common substring plucking both i and j
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i][j-1], lcs[i-1][j]);
                }

            }
        }


        return lcs[s1.length()][s2.length()];

    }

    public static void main(String[] args) {
        System.out.println("Longest Common Subsequence Length :" + findLongestCommonSubsequence(s1,s2));
    }


}
