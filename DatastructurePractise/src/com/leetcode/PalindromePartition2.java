package com.leetcode;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

 Return all possible palindrome partitioning of s.

 For example, given s = "aab"  return
 ["aa","b"],
 ["a","a","b"]

 * Created by abhishekm787 on 8/26/14.
 */
public class PalindromePartition2 {

    public static void partition(String s) {

        boolean [][]isPalindrome  = new boolean[s.length()][s.length()];
        int [][]cost = new int [s.length()][s.length()];

        for( int i =0; i< s.length(); i++) {
            for( int j = 0; j< s.length(); j++) {
                cost[i][j] = Integer.MAX_VALUE - 1000;
            }
        }

        // find all single letter palindrome
        for( int i=0; i< s.length(); i++) {
            isPalindrome[i][i] = true;
            cost[i][i] = 0;
        }

        // find all double letter palindrome
        for( int i = 0; i< s.length()-1; i++) {
            if(s.charAt(i) == s.charAt(i+1)) {
                isPalindrome[i][i+1] = true;
                cost[i][i+1] = 0;
            }
        }

        //find 3 or more letter palindrome
        for( int l=3; l < s.length(); l ++) {
             for( int i = 0; i < s.length()-l+1;i++) {
                 int j = i+l-1;
                 if(s.charAt(i) == s.charAt(j)) {
                     isPalindrome[i][j] = isPalindrome[i+1][j-1];
                     cost[i][j] = cost[i+1][j-1];
                 }  else {
                     for(int k = i; k < j; k++) {
                         if(cost[i][j] > cost[i][k] + cost[k][j] +1) {
                             cost[i][j] = cost[i][k] + cost[k][j] +1;
                         }
                     }
                 }

             }
        }



    }

    public static void main(String[] args) {
        partition("aabaab");
    }

}
