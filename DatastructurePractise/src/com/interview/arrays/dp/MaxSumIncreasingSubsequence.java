package com.interview.arrays.dp;

/**
 * Given a sequence of non-negative integers,
 * find the subsequence of length 3 having maximum su, with the numbers of the subsequence being in ascending order.
 * arr[] = {1, 5, 10, 8, 9}
 * Output: 5 8 9
 * Created by abhimaloo on 7/17/14.
 */
public class MaxSumIncreasingSubsequence {


    public static int[] input = {1, 5, 10, 8, 9};

    public static int[] maxSumIncreasingSubsequence(int[] input, int length){

        int[] response = new int[length];
        /**
         * sum[n][l] is responsible for keeping max sums of
         * sum[i][3] means from i = 0 ..i in input[] , maximum sum of a subsequence of 3 length
         * till i
         */
        int sum[][] = new int[input.length][length+1];

        //reconstruction is crappy as always
        //we will keep back pointers
        int [][]reconstruct = new int[input.length][length+1];

        //base conditions needs to setup
        for(int i =0; i<input.length; i++) {
            // subsequence of zero length will have 0 sum
            sum[i][0] = 0;
            //subsequence of 1 length will have the sum equals to the number itself
            sum[i][1] = input[i];
        }

        //for reconstruction
        int maxSum = 0;
        int maxIndex = -1;

        // goes from element 0 to n.. index of the number
        for(int i=0; i<sum.length; i++) {
            // length of the subsequence
            for(int j = 2; j<=length; j++) {
                // this is to compare all smaller elements
                for(int k =0; k<i;k++){

                    if(input[k]<input[i]){
                        int sumCalculatedByJ ;

                        //checking whether sum is optimal or not
                        if(sum[k][j] < input[i]+ sum[k][j-1]){
                            sumCalculatedByJ = input[i]+ sum[k][j-1];
                        } else {
                            sumCalculatedByJ = sum[k][j];
                        }

                        if(sum[i][j] < sumCalculatedByJ){
                            // mark the node responsible for the maximum value
                            reconstruct[i][j] = k;
                            //update the sum
                            sum[i][j] = sumCalculatedByJ;
                            // this is to track the largest sum and the index responsible for it
                            if(maxSum < sum[i][j]){
                                maxSum = sum[i][j];
                                maxIndex = i;
                            }

                        }

                    }
                }
            }
        }


        // reconstruction logic
        while(length>0){
            response[length-1] = input[maxIndex];
            maxIndex = reconstruct[maxIndex][length--];
        }

        return response;

    }



    public static void main(String args[]) {
        int[] subsequence = maxSumIncreasingSubsequence(input,3);
        for( int i=0; i< subsequence.length; i++){
            System.out.println(subsequence[i]);
        }

    }
}
