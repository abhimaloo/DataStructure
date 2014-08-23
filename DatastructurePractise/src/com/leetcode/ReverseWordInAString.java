package com.leetcode;

/**
 * Given an input string, reverse the string word by word.

 For example,
 Given s = "the sky is blue",
 return "blue is sky the".

 https://oj.leetcode.com/problems/reverse-words-in-a-string/
 * Created by abhimaloo on 8/22/14.
 */
public class ReverseWordInAString {
    public static String input = "the sky is blue";
    public static String reverseWords(char[] input) {
        int left = 0;
        int right = input.length-1;
        //reverse the complete string first
        while(left < right) {
            //swap left and right
            swap(input, left++, right--);
        }

        left = 0;
        // now run till left is less than input's length
        while(left < input.length) {
            // move left to the first non space character
            while(left >=0  && left < input.length && input[left] == ' '){
                left++;
            }
            // start right from left
            right = left;
            // move right to the first space after left
            while(right < input.length && input[right] != ' '){
                right ++;
            }
            // since right is on space , bring right to one character back
            right = right -1;
            // reverse the word represented by left to right
            int leftHead = left;
            int rightTail = right;

            while(leftHead < rightTail) {
                //swap left and right
                swap(input, leftHead++, rightTail--);
            }

            // assign left to the next element of current right
            left = right+1;
        }

        return new String(input);
    }


    private static void swap(char[] input, int left, int right) {
        char temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }

    public static void main(String[] args) {
        System.out.println(reverseWords(input.toCharArray()));
    }

}
