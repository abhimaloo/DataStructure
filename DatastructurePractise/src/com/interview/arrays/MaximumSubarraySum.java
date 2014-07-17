package com.interview.arrays;

/**
 * This is a Divide and Conquer and DP Question
 * You are given a one dimensional array that may contain both positive and negative integers,
 * find the sum of contiguous subarray of numbers which has the largest sum.

 * For example, if the given array is {-2, -5, 6, -2, -3, 1, 5, -6},
 * then the maximum subarray sum is {6, -2, -3, 1, 5}  = 7 (see highlighted elements).
 * Complexity of solution is O(nlogn) for Divide and Conquer while O(n) for DP solution.
 *
 * Created by abhishekm787 on 7/16/14.
 */
public class MaximumSubarraySum {
    public static int[] input = {-2, -5, 6, -2, -3, 1, 5, -6};

    /**
     * Its through Kadane's algorithm using Dynamic programming..
     * try iterating the array from left to right
     * capture any non negative element and start keeping cumulative sum
     * also keep a maz so far to have the maximum value of the maxEnd
     * @param input
     * @return
     */
    public static int findMaximumSum(int[] input){
         int maxEnd = -1;
         int maxSoFar = Integer.MIN_VALUE;

        for(int i=0; i<input.length; i++){
           if(maxEnd<0){
               maxEnd = input[i];
           } else{
               maxEnd += input[i];
           }

           if(maxSoFar <= maxEnd){
               maxSoFar = maxEnd;
           }

        }

        return maxSoFar;


    }


    /**
     * Its is through divide and Conquer
     * Complexity is O(nlogn)
     * @param input
     * @param start
     * @param end
     * @return
     */
    public static int findMaximumSubarraySum(int[] input, int start, int end) {


        //size 1  base case
        if(start ==end){
            return input[start];
        }

        int mid = (start+end)/2;
        return Math.max(Math.max(findMaximumSubarraySum(input, start, mid), findMaximumSubarraySum(input, mid+1, end)), findMaximumSubArraySumCrossing(input, start, mid,end));

    }

    /**
     * tricky method .. we need to calculate the maximum sum subarray of left and right
     * @param input
     * @param start
     * @param mid
     * @param end
     * @return
     */
    private static int findMaximumSubArraySumCrossing(int[] input, int start, int mid, int end) {
        int sum = 0;
        //maxLeft variable will keep the maximum sum
        int maxLeft = Integer.MIN_VALUE;

        for(int i = mid; i>=start; i--){
            // keep cumulative total of sum
            sum += input[i];
            // if cumulative total sum become higher than the max left sum
            //its sort of computing the local maxima of sum
            // include the latest sum as max left
            if(sum > maxLeft){
                maxLeft = sum;
            }
        }

        sum = 0;
        //maxRight will keep the maximum sum for right half
        int maxRight = Integer.MAX_VALUE;

        for(int i= mid+1; i<=end; i++){
            //keep cumulative total of sum
            sum +=input[i];
            //if sum become larger than maxRight, include that sum as maxRight
            if(sum<maxRight){
                maxRight = sum;
            }
        }

        //return sum of maxLeftSum and maxRightSum
        return maxLeft+maxRight;

    }

    public static void main(String[] args) {
        //System.out.println("Sum is :"+findMaximumSubarraySum(input, 0, input.length - 1));
        System.out.println("Sum is :"+findMaximumSum(input));



    }
}
