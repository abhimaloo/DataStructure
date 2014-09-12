package com.revision;

/**
 * String -  "geeksforofskeegismyname" ,  find the longest palindromic subsequence
 * Created by abhishekm787 on 9/11/14.
 */
public class LongestPalindromicSubstring {
    public static String input = "geeksforofskeegismyname";

    /**
     * Idea is consider sub strings of all the lengths in increasing order
     *
     * optimal substructure is  - if you add i and j to a substring i+1 to j-1 ;
     * if i and j are same characters and (i+1, j-1) also forms a palindrome then string(i , j) will be plaindromic as well
     *
     *
     *
     * @param input
     * @return
     */

    public static String findLongest(String input) {
        //maintain this to record
        boolean [][]isPalindrome = new boolean[input.length()][input.length()];
        int maxLength = Integer.MIN_VALUE;
        int start = -1;

        //first find single length palindromes
        for( int i = 0; i< input.length(); i++) {
            isPalindrome[i][i] = true;
            maxLength = 1;
            start = i;
        }

        //Double length palindromes
        for( int i = 1; i< input.length(); i++) {
            if(input.charAt(i) == input.charAt(i-1)) {
                isPalindrome[i-1][i] = true;
                maxLength = 2;
                start = i-1;
            }
        }

        // for lengths 3 and more
        for( int l = 3; l<= input.length(); l++) {
            for( int i = 0; i<input.length()-l+1; i++) {
                int j = l+i-1;
                // check if first and last characters match or not and string by plucking both of them is also palindrome
                if(input.charAt(i) == input.charAt(j) &&  isPalindrome[i+1][j-1]) {
                    // mark is palindrome to true
                    isPalindrome[i][j] = true;
                    // update maxLength if required
                    if(l > maxLength){
                        maxLength = l;
                        start = i;
                    }
                }

            }
        }


        return input.substring(start, start+maxLength);

    }

    public static void main(String[] args) {
        System.out.println(findLongest(input));
    }

}
