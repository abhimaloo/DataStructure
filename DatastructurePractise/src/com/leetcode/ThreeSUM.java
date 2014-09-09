package com.leetcode;

import com.interview.sorting.QuickSort;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = SUM? Find all unique triplets in the array which gives the sum of SUM.
 * Created by abhimaloo on 9/8/14.
 */
public class ThreeSUM {

    public static int[] input = {1,6,2,4,9};

    public static int sum = 19;

    public static void threeSum(int[] input, int sum) {
        //first of all sort the input
        QuickSort.sort(input, 0, input.length -1);

        for( int i = 0; i< input.length ; i++) {

            int start = i+1;
            int end = input.length -1;
            while (start<end) {
                if(input[i] + input[start]+input[end] == sum) {
                    System.out.println("Sum :"+ sum + "  = "+ input[i] + " "+ input[start] + " "+ input[end]);
                    return;
                } else if(input[i] + input[start]+input[end] < sum) {
                    start++;
                } else {
                    end --;
                }
            }
        }

    }

    public static void main(String[] args) {
        threeSum(input, sum);
    }
}
