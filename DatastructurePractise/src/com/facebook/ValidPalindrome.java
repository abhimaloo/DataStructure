package com.facebook;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 For example,
 "A man, a plan, a canal: Panama" is a palindrome.
 "race a car" is not a palindrome.

 Note:
 Have you consider that the string might be empty? This is a good question to ask during an interview.

 For the purpose of this problem, we define empty string as valid palindrome.
 * Created by abhimaloo on 9/27/14.
 */
public class ValidPalindrome {

    public static String input = "A man, a plan, a canal: Panama";
    public static boolean validPalindrome(String s) {
        if(s == null) {
            return false;
        }

        int left = 0;
        int right = s.length()-1;

        while(left < right) {
            while(left < right && !Character.isLetterOrDigit(left)){
                left ++;
            }

            while(left < right && !Character.isLetterOrDigit(right)){
                right --;
            }

            if(left < right && s.charAt(left) != s.charAt(right)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(validPalindrome(input));
    }


}
