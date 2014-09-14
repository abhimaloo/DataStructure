package com.revision;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

 Return the minimum cuts needed for a palindrome partitioning of s.

 For example, given s = "aab",
 Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

 * Created by abhimaloo on 9/11/14.
 */
public class PalindromePartitioningII {

    public static String s = "aab";

    public static int minCut(String s) {
        boolean [][] isPal = new boolean[s.length()][s.length()];
        int[][] cost = new int[s.length()][s.length()];

        // for single letter palindromes
        for( int i = 0; i< s.length(); i++) {
            isPal[i][i] = true;
            cost[i][i] = 0;
        }

        // for 2 and more lettered string
        for( int l = 2; l<= s.length(); l++) {
            for(int i = 0; i< s.length()-l+1; i++) {
                int j = l+i -1;
                 // for length 2
                if(l ==2) {
                    isPal[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                   // for length 3+
                   isPal[i][j] =  s.charAt(i) == s.charAt(j) && isPal[i+1][j-1];
                }

                if(isPal[i][j]) {
                    cost[i][j] = 0;
                } else {
                    // if not palindrome ..then try all the potential cuts with minimum cost which can make it palindromic
                    cost[i][j] = Integer.MAX_VALUE;
                    for( int k = i; k<j; k++) {
                        if(cost[i][j] > cost[i][k]+ cost[k+1][j] + 1) {   // here +1 defines a cost of a cut
                            cost[i][j] = cost[i][k]+ cost[k+1][j] + 1;
                        }
                    }
                }

            }
        }

        return cost[0][s.length()-1];
    }

    public static void main(String[] args) {
        System.out.println(minCut(s));
    }
}
