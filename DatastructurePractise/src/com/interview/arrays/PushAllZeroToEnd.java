package com.interview.arrays;

/**
 * Given an array of random numbers, Push all the zero’s of a given array to the end of the array.
 * For example, if the given arrays is {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0},
 * it should be changed to {1, 9, 8, 4, 2, 7, 6, 0, 0, 0, 0}
 * Created by abhishekm787 on 7/17/14.
 */
public class PushAllZeroToEnd {
    public static int[] input = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0};

    /**
     * trick is to move a left pointer and a right pointer
     * keep advancing them till left has non zero elements
     * and right has zero elements.
     * whenever we encounter anamoly we swap the digits.
     * @param input
     */
    public static void push(int[] input){
        int left = 0;
        int right = input.length-1;
        while(left<right){
            while(input[left]!=0 && left<right){
                left++;
            }

            while(input[right]==0 && left<right){
                right--;
            }

            //swap left with right
            int temp = input[left];
            input[left] = input[right];
            input[right] = temp;
        }

    }

    public static void main(String[] args) {
        push(input);
    }
}
