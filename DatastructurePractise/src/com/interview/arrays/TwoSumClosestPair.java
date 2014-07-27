package com.interview.arrays;

import com.maloo.sorting.QuickSort;

/**
 * An Array of integers is given, both +ve and -ve.
 * You need to find the two elements such that their sum is closest to zero.
 *
 * {1, 60, -10, 70, -80, 85}
 * Output - -80 and 85
 * Created by abhimaloo on 7/26/14.
 */
public class TwoSumClosestPair {

    public static int [] input = {1, 60, -10, 70, -80, 85};

    /**
     * Sorting does the major work
     * trick is to follow these steps -
     * a) Sort the array
     * b) keep minL and minR variables and minSum
     * c) iterate the array from left to right
     * d) pick index from both the end and compare the sum of them against minSum
     * e) update the minSum if it is changing and update minL and minR
     * f) if the current sum of both the ends is less than 0 .. it means you should increase left pointer
     * g) if current sum is greater than zero decrease right pointer
     * @param input
     */
    public static void findClosestPair(int[] input) {
        // sort the array
        QuickSort.quickSort(input,0,input.length-1);
        int left = 0;
        int right = input.length-1;
        int minSum = Integer.MAX_VALUE;
        int minL = 0;
        int minR = input.length-1;
        while(left<right){
            int sum = input[left] + input[right];
            if(Math.abs(sum) < minSum){
                minSum = Math.abs(sum);
                minL = left;
                minR = right;
            }

            if(sum < 0) {
                left++;
            } else {
               right --;
            }
        }

        System.out.println("pair with sum closest to 0 is :"+ input[minL]+ " and "+ input[minR]);

    }

    public static void main(String[] args) {
        findClosestPair(input);
    }
}
