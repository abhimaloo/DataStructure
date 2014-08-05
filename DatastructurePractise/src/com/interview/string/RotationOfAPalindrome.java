package com.interview.string;

/**
 * Given a string, check if it is a rotation of a palindrome.
 * For example your function should return true for “aab” as it is a rotation of “aba”.
 *
 * http://www.geeksforgeeks.org/check-given-string-rotation-palindrome/
 * Created by abhishekm787 on 8/4/14.
 */
public class RotationOfAPalindrome {

    /**
     * Brute force approach -
     * try all the possible rotations and check each for being palindrome or not
     * @param input
     * @return
     */
    public static boolean isRotatedPanlindrome(String input) {
         for( int i = input.length()-1; i>=0; i--){
             String prefix = input.substring(0,input.length()-1);
             input = input.charAt(input.length()-1) + prefix;
             if(isPalindrome(input)) {
                 return true;
             }
         }

        return false;
    }

    private static boolean isPalindrome(String input) {
        int left = 0 ;
        int right = input.length()-1;
        while(left<=right) {
            if(input.charAt(left++) != input.charAt(right--)){
                return false;
            }
        }

        return true;
    }

    /**
     * Idea is to append input to input itself. then find longest palindromic sequence of length n(where n is input length)
     * example - aab will be appended with aab .. so new string would be aabaab ..now we will find
     * longest palindromic sequence of length 3 in it.
     * @param input
     * @return
     */
    public static boolean isRotatedPalindromeDP(String input) {
        String augmentedInput = input + input;
        boolean[][] length = new boolean[augmentedInput.length()+1][augmentedInput.length()+1];
        int maxLength = input.length();
        //setting up base case  .. all single letter palindromes
        for( int i=0; i< augmentedInput.length(); i++) {
            length[i][i] = true;
        }

         //all double letter palindromes
        for( int i=1; i< augmentedInput.length(); i++) {
            if(augmentedInput.charAt(i) == augmentedInput.charAt(i-1)){
                length[i-1][i] = true;
                if(maxLength ==2) {
                    return true;
                }
            }
        }

        //for all palindrome with length 3 or above
        for( int j=3 ;j <=maxLength; j++) {
            for( int i =0; i<augmentedInput.length()-j+1; i++) {
                int k = i+j-1;
                if(augmentedInput.charAt(i) == augmentedInput.charAt(k) && length[i+1][k-1]) {
                    length[i][k] = length[i+1][k-1];
                    if(j == maxLength){
                        return true;
                    }
                }

            }
        }

        return false;

    }


    public static void main(String[] args) {
        //System.out.println(isRotatedPanlindrome("aaaad"));
        System.out.println(isRotatedPalindromeDP("aaaad"));
    }
}
