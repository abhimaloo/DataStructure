package com.revision;

/**
 * Created by abhimaloo on 9/14/14.
 */
public class MaximumSubarraySum {
    public static int[] input = {-2, -5,4,10,-1,1, 6, -2, -3, 1, 5, -6};

    /**
     * Trick is to use DP ..
     * Sum[i] = Max(sum[i-1] + a[i], a[i])
     *
     * Very simple to understand if adding an element to the sum makes the sum -ve or less than a[i] then break that running sum and start afresh
     *
     * @param input
     * @return
     */
    public static int findMaxSumContigous( int[] input) {
        int maxSumSoFar = input[0];
        int runningSum = input[0];
        // iterate from element 1 to n
        for( int i=1; i< input.length; i++) {
            // assign running sum to max of running sum + a[i] or a[i]
            runningSum = Math.max(runningSum + input[i], input[i]);
            // update maxSiumSoFar if required
            maxSumSoFar = Math.max(maxSumSoFar, runningSum);
        }

        return maxSumSoFar;
    }

    public static int maxSum(int[] input) {
        int maxSumSoFar = input[0];
        int sum = input[0];
        for(int  i = 1; i< input.length; i++) {
            if(input[i] + sum < 0) {
                sum = 0;
            } else {
                sum += input[i];

                if(sum > maxSumSoFar) {
                    maxSumSoFar = sum;
                }
            }
        }

        return maxSumSoFar;

    }



    public static void main(String[] args) {
        System.out.println(maxSum(input));
        System.out.println(findMaxSumContigous(input));
    }

}
