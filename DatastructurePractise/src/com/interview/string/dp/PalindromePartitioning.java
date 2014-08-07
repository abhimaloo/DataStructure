package com.interview.string.dp;

/**
 * Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome.
 * For example, “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
 * Determine the fewest cuts needed for palindrome partitioning of a given string.
 *
 * Ex - ababbbabbababa
 * Output 3
 *
 * http://www.geeksforgeeks.org/dynamic-programming-set-17-palindrome-partitioning/
 * Created by abhimaloo on 8/6/14.
 */
public class PalindromePartitioning {
    public static String input = "ababbbabbababa";


    /**
     * trick is to use DP ..similar to matrixChainMultiplication or LongestPalindromicSubsequence
     * we maintain two double dimensional arrays costs[][] and isPalindrome[][]
     * iterate through all size of palindromes and find out if they are palindrome or not
     * if not try all ks and find the minimum cost of partition
     * if yes set cost as 0
     * @param input
     * @return
     */
    public static int findPalindromeMinCut(String input) {
        int[][]cost = new int[input.length()][input.length()];
        boolean [][] isPalindrome = new boolean[input.length()][input.length()];

        //base case all single length elements are palindromes
        for( int i=0; i< input.length(); i++) {
            isPalindrome[i][i] = true;
            cost[i][i]  =0;
        }

        //all double and more length palindromes
        for( int l=2; l<=input.length(); l++) {
            for( int i=0; i< input.length()-l+1; i++) {
                int j= i+l-1;
                //for double lengthed strings
                if(l==2) {
                    //just check the two characters for equality
                    isPalindrome[i][j] = input.charAt(i)==input.charAt(j);
                } else {
                    //for more than 2 chars ..both the ends should be equal and i+1 and j-1 should also be a palindrome
                    isPalindrome[i][j] = (input.charAt(i)==input.charAt(j)) && isPalindrome[i+1][j-1];
                }

                if(isPalindrome[i][j]){
                    cost[i][j] = 0;
                } else {
                    //find all the possible partition and their associated costs
                    cost[i][j] = Integer.MAX_VALUE;
                    //iterate for all the k's and find minimum cost
                    for(int k=i; k<j ; k++) {
                        if( cost[i][j] > cost[i][k]+ cost[k+1][j] + 1){
                            cost[i][j] = cost[i][k]+ cost[k+1][j] + 1;
                        }
                    }
                }
            }
        }

        return cost[0][input.length()-1];
    }

    public static void main(String[] args) {
        System.out.println(findPalindromeMinCut(input));
    }

}
