package com.interview.arrays;

/**
 * Given an array A[], write a function that segregates even and odd numbers.
 * The functions should put all even numbers first, and then odd numbers.
 * Example
 * Input = {12, 34, 45, 9, 8, 90, 3}
 * Output = {12, 34, 8, 90, 45, 9, 3}
 * Created by abhimaloo on 7/26/14.
 */
public class SegregateEvenAndOddNumbers {
    public static int [] input = {12, 34, 45, 9, 8, 90, 3};

    public static void segregateEvenAndOdd(int[] input) {
        int left = 0;
        int right = input.length -1;

        while(left<right){
            while(left<right && input[left] %2 == 0){
                left++;
            }

            while(left<right && input[right] %2 ==1) {
                right --;
            }

            if(left <right){
                int temp = input[left];
                input[left] = input[right];
                input[right] = temp;
                left ++;
                right --;
            }

        }

    }

    public static void main(String[] args) {
        segregateEvenAndOdd(input);
    }

}
