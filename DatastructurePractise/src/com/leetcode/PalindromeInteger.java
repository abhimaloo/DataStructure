package com.leetcode;

/**
 * Check whether a number is palindrome or not
 * Created by abhimaloo on 9/24/14.
 */
public class PalindromeInteger {
    public static boolean isPalindrome(int a) {

        if(a < 0) {
            a = 0 - a;
        }
        int n = a;
        int result = 0;
        while(a > 0){
            result = result*10 + a%10;
            a = a/10;
        }
        return n == result;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(123321));
    }


}
