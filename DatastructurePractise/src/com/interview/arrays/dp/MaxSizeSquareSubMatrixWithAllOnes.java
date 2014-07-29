package com.interview.arrays.dp;

/**
 * Given a binary matrix, find out the maximum size square sub-matrix with all 1s.
 * 0  1  1  0  1
 * 1  1  0  1  0
 * 0  1  1  1  0
 * 1  1  1  1  0
 * 1  1  1  1  1
 * 0  0  0  0  0
 *
 * output -
 * 1  1  1
 * 1  1  1
 * 1  1  1
 *
 *
 * Created by abhimaloo on 7/26/14.
 */
public class MaxSizeSquareSubMatrixWithAllOnes {
    public static int[][] input = {{0,1,1,0,1},
                                   {1,1,0,1,0},
                                   {0,1,1,1,0},
                                   {1,1,1,1,0},
                                   {1,1,1,1,1},
                                   {0,0,0,0,0}};

    /**
     * trick is to use DP
     * lets maintain a two D array of sum with same dimensions as input
     * every entry in sum[i][j] will keep the sum of matrix of all 1's which can be created
     * by keeping input[i][j] as bottomRight corner
     *
     * We will keep calculating the sum by this recurrnce -
     * if(input[i][j] ==1) then
     * sum[i][j]  = min(sum[i-1][j], sum[i][j-1], sum[i-1][j-1]) +1
     *
     * else sum[i][j] will be 0 .
     *
     * finally find the largest sum and print a sqaure matrix of that sum as dimension
     *
     *
     *
     * @param input
     */
    public static void printSubmatrix(int[][] input){
        int[][] sum = new int[input.length][input[0].length];
        int maxSum = Integer.MIN_VALUE;
        //copy base case first row and first column as is
        for(int i=0;i<input.length; i++){
            sum[i][0] = input[i][0];
        }
        for(int i=0; i< input[0].length; i++){
            sum[0][i] = input[0][i];
        }

        for(int i =1; i< input.length; i++){
            for(int j=1;j<input[0].length; j++){
                //find minimum of all the three places and increase the size by adding a 1
                if(input[i][j] ==1){
                    sum[i][j] = Math.min(Math.min(sum[i-1][j], sum[i][j-1]), sum[i-1][j-1]) +1;
                    if(sum[i][j] > maxSum){
                        maxSum = sum[i][j];
                    }
                } else {
                    sum[i][j] = 0;
                }

            }
        }

        for(int i=0; i< maxSum; i++){
            for (int j=0; j< maxSum; j++){
                System.out.print(" 1 ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        printSubmatrix(input);
    }
}
