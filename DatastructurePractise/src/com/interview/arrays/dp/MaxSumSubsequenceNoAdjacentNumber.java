package com.interview.arrays.dp;

/**
 * Given an array of positive numbers, find the maximum sum of a subsequence
 * with the constraint that no 2 numbers in the sequence should be adjacent in the array
 *
 * ex - 3 2 5 10 7
 * output return 15 (sum of 3, 5 and 7)
 * Created by abhimaloo on 7/26/14.
 */
public class MaxSumSubsequenceNoAdjacentNumber {
    public static int[] input = {3,2,5,10,7};

    /**
     * trick is like DP
     *
     * Consider a sequence .. at any index you have to think .should i chose this index as a part of solution or not
     * To make this choice you will either select this index which means you cannot select the previous index which is
     * sumInc - sumExcluding(lastStep) + a[i].  OR you will not select this index where the sum would be
     * sumExc - Max(sumExcludingLastStep, sumIncludingLastStep)
     *
     * hence at any point you will calculate these two values and pick the one which results into max sum
     * that's why you finally take the max(sumInc and sumExc)
     *
     *
     * keep two values  - sumInc (sum including the current value) and sumExc (sum excluding the current value)
     * sumInc - sumExc(lastStep)  + input[i]
     * sumExc - Max(sumInc(lastStep), sumExc(LastStep))
     *
     * finally the biggest sum would be Max(sumInc, sumExc)
     *
     * @param input
     * @return
     */
    public static int findMaxSum(int[] input){
        int sumInc = input[0];
        int sumExc = 0;
        for(int i=1; i<input.length ; i++) {
            int temp = Math.max(sumInc, sumExc);
            sumInc = sumExc + input[i];
            sumExc = temp;
        }

        return Math.max(sumInc,sumExc);

    }

    public static void main(String[] args) {
        System.out.println(findMaxSum(input));
    }
}
