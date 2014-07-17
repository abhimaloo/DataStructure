package com.interview.arrays.dp;

/**
 * /**
 * Given a sequence of non-negative integers,
 * find the subsequence of length 3 having maximum product, with the numbers of the subsequence being in ascending order.
 * arr[] = {1, 5, 10, 8, 9}
 * Output: 5 8 9
 * Created by abhimaloo on 7/17/14.

 */
public class MaximumProductIncreasingSubsequence {

    public static int[] input = {1, 5, 10, 8, 9};

    public static int[] maxProductIncreasingSubsequence(int[] input, int length){

        int[] response = new int[length];
        /**
         * product[n][l] is responsible for keeping max products of
         * product[i][3] means from i = 0 ..i in input[] , maximum product of a subsequence of 3 length
         * till i
         */
        int product[][] = new int[input.length][length+1];

        //reconstruction is crappy as always
        //we will keep back pointers
        int [][]reconstruct = new int[input.length][length+1];

        //base conditions needs to setup
        for(int i =0; i<input.length; i++) {
            // subsequence of zero length will have 0 product
            product[i][0] = 0;
            //subsequence of 1 length will have the product equals to the number itself
            product[i][1] = input[i];
        }

        //for reconstruction
        int maxProduct = 0;
        int maxIndex = -1;

        // goes from element 0 to n.. index of the number
        for(int i=0; i<product.length; i++) {
            // length of the subsequence
            for(int j = 2; j<=length; j++) {
                // this is to compare all smaller elements
                for(int k =0; k<i;k++){

                    if(input[k]<input[i]){
                        int productCalculatedByJ ;

                        //checking whether product is optimal or not
                        if(product[k][j] < input[i] * product[k][j-1]){
                            productCalculatedByJ = input[i]* product[k][j-1];
                        } else {
                            productCalculatedByJ = product[k][j];
                        }

                        if(product[i][j] < productCalculatedByJ){
                            // mark the node responsible for the maximum value
                            reconstruct[i][j] = k;
                            //update the sum
                            product[i][j] = productCalculatedByJ;
                            // this is to track the largest product and the index responsible for it
                            if(maxProduct < product[i][j]){
                                maxProduct = product[i][j];
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
        int[] subsequence = maxProductIncreasingSubsequence(input,3);
        for( int i=0; i< subsequence.length; i++){
            System.out.println(subsequence[i]);
        }

    }
}
