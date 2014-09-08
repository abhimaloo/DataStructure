package com.leetcode;

/**
 * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * https://oj.leetcode.com/problems/longest-palindromic-substring/
 * Created by abhimaloo on 9/8/14.
 */
public class LongestPalindromicSubstring {
    public static String input = "forgeeksskeegfor";

    /**
     * Trick is to keep a boolean variable palindrom[i,j]which denotes if string from i to j is a palindrome or not
     * also keep longestPalindrome length.
     * start by checking single letter palindromes and double letter palindromes
     *
     * then for 3 letter palindomes check if a[i] == a[j] or not and palidrome[i+1][j-1] is true
     * if yes, update the longest palindom if necessary and set palidnorm[i][j] = true;
     * else set palindorm[i][j]  = false
     *
     * finally return the longest palindrom length
     * @param input
     * @return
     */
    public static int findLongestPalindromicSubstring(char[] input) {

        boolean[][]palindrom = new boolean[input.length][input.length];

        int longestPalindome = 0;
        // all single letter palindrome
        for( int i = 0; i <  input.length ; i++) {
            palindrom[i][i] = true;
            longestPalindome = 1;
        }

        // all double letter palindromes
        for( int i = 1; i < input.length ; i++) {
            if(input[i-1] == input[i]) {
                palindrom[i-1][i] = true;
                longestPalindome = 2;
            }
        }

        // from l = 3 to input.length
        for( int l = 3; l <= input.length ; l++) {
            // i would be from 0 to length -l +1
            for( int i = 0; i< input.length - l+1; i++) {
                // j would i+l -1;
                int j = i + l - 1;
                //check if i and j  character match or not  and i +1 and j-1 is palidnorme or not
                if(input[i] == input[j] && palindrom[i+1][j-1]) {
                    // update the palindrome
                    palindrom[i][j] = palindrom[i+1][j-1];
                    //update the longest palindrom if required
                    if(longestPalindome < l) {
                        longestPalindome = l;
                    }
                } else {
                    // update the palindrome to be false
                    palindrom[i][j] = false;
                }
            }
        }

        return longestPalindome;
    }

    public static void main(String[] args) {
        System.out.println(findLongestPalindromicSubstring(input.toCharArray()));
    }
}
