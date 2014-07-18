package com.interview.arrays.dp;

import java.util.Arrays;

/**
 * Given a string, find the longest substring which is palindrome.
 * For example, if the given string is “forgeeksskeegfor”,
 * the output should be “geeksskeeg”.
 * Created by abhishekm787 on 7/17/14.
 */
public class LongestPalindromeSubstring {


    /**
     * trick is to apply DP
     * maintain a boolean[][] for every i to j index of substring
     * if substring between input[i] to input[j] contains palindrome mark it to be true
     * @param input
     * @return
     */
    public static char[] findLongestPalindrome(char[] input){
         boolean palindrome[][] = new boolean[input.length][input.length];
         int start = -1;
         int maxlength = 0;

         //fill all single letter palindrome
        for(int i=0; i< input.length; i++){
            palindrome[i][i] = true;
        }

        //for all double letter palindrome
        for(int i=0; i< input.length-1; i++) {
            if(input[i] == input[i+1]) {
                palindrome[i][i+1] = true;
                start = i;
                maxlength = 2;
            }
        }


        //now check for lengths 3 to input.length
        for( int k = 3; k<= input.length; k++) {
            for( int i=0; i< input.length - k+1; i++) {
                int j = i+k-1;

                // important is to understand that if p[i+1][j-1] is palindrome than
                // p[i][j] will be palindrome if input[i] == input[j]
                if(input[i] == input[j] && palindrome[i+1][j-1]){
                    palindrome[i][j] = true;
                    if(maxlength < k){
                        maxlength = k;
                        start = i;
                    }
                }


            }
        }


        return Arrays.copyOfRange(input,start,start+maxlength);


    }


    public static void main(String[] args) {
        char[] palindrome = findLongestPalindrome("forgeeksskeegfor".toCharArray());
        for(int i = 0;i<palindrome.length;i++){
            System.out.print(palindrome[i]);
        }
    }
}
