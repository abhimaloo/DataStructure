package com.maloo.dynamicprogramming;

/**   TYpical dynamic programing question
 * apply a little bit greedy approach as well
 * Given coins with value v1..vn
 * find out minimum number of coins required to have a sum S
 * Created by abhishekm787 on 7/7/14.
 */
public class MinimumSumCoinSelection {
    public static int[] values = {1,5,10};


    /**
     * Main trick  - sort the value array in increasing order
     * try calculating the minimum number of coins required for every sum like 1 to S
     * if a coin is part of the solution then minimal coin for reduced value +1 should be the newest minimum for a required sum
     * else do nothing..
     * @param sum
     */
    public static void minimumCoin(int sum) {
        int[] minNumberOfCoin = new int[sum+1];

        //assign  the minimum value for all the sums to be infinite
        for(int i =0; i<=sum;i++){
            minNumberOfCoin[i] = Integer.MAX_VALUE;
        }
        //for zero sum you need zero coins
        minNumberOfCoin[0] = 0;


        // itearte for sum 1 to S
        for(int i=1; i <= sum ; i ++){
            //consider each coin in decreasing order of the value since that will minimize the number of coins used
            //greedy aproach
            for(int j = values.length-1; j >=0; j--) {

                //if the coin value is less than or equals to sum..
                // minimum number of coins required for this sum would be minimum of ( 1 + minCoin(sum-value(coin)) or an already present value)
                if(values[j] <= i) {
                    minNumberOfCoin[i] = Math.min(1+ minNumberOfCoin[i-values[j]], minNumberOfCoin[i]);
                }
            }
        }

        System.out.println(minNumberOfCoin[sum]);

       // reconstruction
        for(int j = values.length-1; j >=0; j--) {
            //iterate through coins in decreasing order
            //if coin value is less then sum ..try using the coin as much till the moment you cannot use this coin anymore
            //which means sum is less than coin value..
            //give chance to a lower denomination coin now
            while(values[j] <= sum) {

                System.out.print(" "+values[j]+ " ");

                sum = sum - values[j];
            }
        }


    }


    public static void main(String[] args) {
        minimumCoin(8);
    }

}
