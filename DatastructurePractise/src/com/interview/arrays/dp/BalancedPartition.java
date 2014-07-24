package com.interview.arrays.dp;

/**
 * Partition problem is to determine whether a given set can be partitioned
 * into two subsets such that the sum of elements in both subsets is same.
 *
 * arr[] = {1, 5, 11, 5}
 Output: true
 The array can be partitioned as {1, 5, 5} and {11}
 * Created by abhishekm787 on 7/23/14.
 */
public class BalancedPartition {
    public static int[] input = {1,5,11,5};

    public static int findSum(int[] input) {
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];

        }
        return sum;
    }

    /**
     * trick is to use DP .. very intelligent trick
     * construct a 2d array called boolean sum[i][j] - where i is the sum and
     * j represents elements from 0 to index j
     *
     * ex - sum[2][3] - means among subset of elements 0 ..3 a sum of "2" exists
     * Applying optimal substructure -
     * element at index j will have sum s if index j-1 has sum s(inherit from last one)
     * or sum - val(i) is present on j-1 index.
     *
     * ..apply above logic
     *
     * @param input
     * @return
     */
    public static boolean partition(int[] input){
        int totalSum = findSum(input);
        //if some is odd, there cannot be a partition
        if(totalSum%2==1) {
            return false;
        }

        int halfSum = totalSum/2;

        //sum array describes sum[a][b][c] where a - 0...index of item , b - length, c - sum
        boolean [][] sum = new boolean[halfSum+1][input.length];

        for( int i=0; i< input.length; i++){
            sum[0][i] = true;
        }


        for( int i=1; i< halfSum+1; i++) {
            for (int j =0; j< input.length; j++){

                sum[i][j] = j-1 >= 0? sum[i][j-1]: false;

                if(i == input[j]){
                    sum[i][j] = true;
                }
                if( i>input[j]){
                    sum[i][j] = sum[i][j] || j-1>=0?sum[i-input[j]][j-1]: false;

                }


            }
        }


        return sum[halfSum][input.length-1];
    }

    public static void main(String[] args) {
        System.out.println(partition(input));
    }
}
