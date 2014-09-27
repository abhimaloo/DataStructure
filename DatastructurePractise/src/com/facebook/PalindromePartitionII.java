package com.facebook;

/**
 * Created by abhimaloo on 9/27/14.
 */
public class PalindromePartitionII {
    public static String s = "aaba";

    public static int partition(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        int[][] cost = new int[s.length()][s.length()];

        //for single letter palindromes
        for(int i=0; i< s.length(); i++) {
            isPalindrome[i][i] = true;
            cost[i][i] = 0;
        }

        //for double and multi length palindromes
        for(int l = 2; l <= s.length(); l++) {
            for( int i = 0; i< s.length() - l+1; i++) {
                int j = i+l -1;

                if(l ==2) {
                    isPalindrome[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    isPalindrome[i][j] = s.charAt(i) == s.charAt(j) && isPalindrome[i+1][j-1];
                }

                if(isPalindrome[i][j]) {
                    cost[i][j] = 0;
                } else {
                    cost[i][j] = Integer.MAX_VALUE;

                    for(int k = i; k< j; k++) {
                        cost[i][j] = Math.min(cost[i][k] + cost[k+1][j] +1, cost[i][j]);
                    }

                }
            }
        }

        return cost[0][s.length()-1];

    }

    public static void main(String[] args) {
        System.out.println(partition(s));
    }

}
