package com.leetcode;

import com.interview.sorting.QuickSort;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 *
 *
 https://oj.leetcode.com/problems/3sum-closest/
 * Created by abhimaloo on 9/8/14.
 */
public class ThreeSumClosest {

    public static int[] input = {1,6,2,4,9};

    public static int sum = 10;

    /**
     * Almost like 3 SUM problem. while checking perfect sum, also record the min differemce and the sum which caused the min difference in each state
     * @param input
     * @param target
     */
    public static int findThreeSumClosest(int[] input, int target) {
        // sort the array first
        QuickSort.sort(input, 0 , input.length -1);
        // set the min value
        int minDiff = Integer.MAX_VALUE;
        int result = 0;
        // iterate from left to right
        for( int i = 0; i< input.length; i++) {
            //like 3 sum
            int start = i+1;
            int end  =  input.length -1;

            while(start < end) {

                int sum = input[i] + input[start] + input[end];
                // if sum becomes target return .. nothing can be closer than this
                if(sum == target) {
                    minDiff = 0;
                    result = target;
                    return result;
                } else if(sum < target) {
                     // check if difference is smaller than minDiff
                    if(target - sum < minDiff) {
                        // update the minSum
                        minDiff = target - sum;
                        //record the minimum sum
                        result = sum;
                    }

                    start ++;

                } else {
                    // if sum is greater than target ..update the minDiffernce and result
                    if(sum - target < minDiff) {
                        minDiff = sum - target;
                        result = sum;
                    }

                    end --;
                }
            }


        }


       return result;
    }

    public static void main(String[] args) {
        System.out.println(findThreeSumClosest(input, sum));
    }

}
