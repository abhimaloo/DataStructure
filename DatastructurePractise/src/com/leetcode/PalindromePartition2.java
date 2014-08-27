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

    public static int partition(String s) {

        boolean [][]isPalindrome  = new boolean[s.length()][s.length()];
        int [][]cost = new int [s.length()][s.length()];


        // find all single letter palindrome
        for( int i=0; i< s.length(); i++) {
            isPalindrome[i][i] = true;
            cost[i][i] = 0;
        }


        //find 2 or more letter palindrome
        for( int l=2; l <= s.length(); l ++) {
             for( int i = 0; i < s.length()-l+1;i++) {
                 int j = i+l-1;
                 if(l ==2) {
                     isPalindrome[i][j] =  s.charAt(i) == s.charAt(j);
                 } else {
                     isPalindrome[i][j] = s.charAt(i) == s.charAt(j) && isPalindrome[i+1][j-1];
                 }
                 // if i to j is palindrome, cost would be 0
                 if(isPalindrome[i][j]) {
                     cost[i][j] = 0;
                 } else {
                     //find all the possible partition and their associated costs
                     cost[i][j] = Integer.MAX_VALUE;
                     // try to find the k which could start from i till j (j exclusive)
                     for(int k = i; k < j; k++) {
                         // if cost of i to k + cost of k+1 to j is less than cost of i to j ..
                         if(cost[i][j] > cost[i][k] + cost[k+1][j] +1) {
                             cost[i][j] = cost[i][k] + cost[k+1][j] +1;
                         }
                     }
                 }

             }
        }

        return cost[0][s.length()-1];

    }

    public static void main(String[] args) {
        System.out.println(partition("aabaab"));
    }

}
