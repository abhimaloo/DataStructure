package com.leetcode;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 *
 * https://oj.leetcode.com/problems/palindrome-number/
 * Created by abhimaloo on 9/8/14.
 */
public class PalindromNumber {
    public static int number = 1221;

    /**
     * Trick is to reverse the number and compare reversed and original number
     * @param number
     * @return
     */
     public static boolean isPalidnromeNumber(int number) {
         int originalnumber = number;
         //first reverse the number
         boolean isNegative = false;
         if(number < 0) {
             isNegative = true;
             number *= -1;
         }
         int reversed = 0;
         //reversing the number
         while(number > 0) {
             reversed  = reversed*10 + number%10;
             number = number/10;
         }

         if(isNegative) {
             reversed *= -1;
         }

         // checking if reversed is same as original number or not.
         return reversed == originalnumber;
     }

    public static void main(String[] args) {
        System.out.println(isPalidnromeNumber(number));
    }

}
