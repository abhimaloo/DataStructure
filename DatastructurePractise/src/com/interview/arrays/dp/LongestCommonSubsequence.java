package com.interview.arrays.dp;

/**
 * Given two sequences, find the length of longest subsequence present in both of them
 * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
 * Created by abhishekm787 on 7/17/14.
 */
public class LongestCommonSubsequence {

    /**
     * Trick is to use dynamic programming
     * maintain an array called length[m][n] where m denotes
     * substring from 0 ..m-1 of s1 and n denotes 0...n-1 of s2
     * length array will contain the length of matching substring till m and n.
     * @param s1
     * @param s2
     * @return
     */
    public static int longestCommonSubsequence(char[] s1, char[] s2){

        int[][] length = new int[s1.length][s2.length];


        for(int i=0;i<s1.length; i++){
            for( int j=0; j< s2.length; j++) {
                // if the characters match
                 if(s1[i] == s2[j]){
                     // increase the length by 1 since i-1 and j-1 substrings
                     length[i][j] = 1+ (i-1>=0 && j-1>=0 ? length[i-1][j-1]: 0);

                 } else {
                     //otherwise find maximum of matching length by adding the last character on both the sides
                     length[i][j] = Math.max(j-1>=0?length[i][j-1]:0, i-1>=0?length[i-1][j]:0);
                 }

            }
        }

       return length[s1.length-1][s2.length-1];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("ABCDGH".toCharArray(), "AEDFHR".toCharArray()));
    }
}
