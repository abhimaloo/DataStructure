package com.maloo.dynamicprogramming;

/** Classic DP problem
 * a burglar breaks into a museum ..
 * he has a knapsack of capacity W
 * There are n items in museum with weights wi and value vi
 * Created by abhishekm787 on 7/7/14.
 */
public class KnapsackProblem {
    public static int[] itemValues = { 1,5,7,8,2,3};
    public static int[] itemWeights = {1,2,3,1,2,1};

    public static void knapsack(int knapsackCapacity) {

        int maxValCapacity [][] = new int[itemValues.length+1][knapsackCapacity+1];

        //initialize maxValCapacity for zero items and remaining available capacities

        for(int i =0; i<=knapsackCapacity; i++ ){
            maxValCapacity[0][i] = 0;
        }

        for( int i = 0; i <itemValues.length; i ++) {


            for(int redC = 0; redC<=knapsackCapacity; redC++ ){

                maxValCapacity[i+1][redC] = Math.max(maxValCapacity[i][redC],redC-itemWeights[i]<0?Integer.MIN_VALUE: (maxValCapacity[i][redC-itemWeights[i]] + itemValues[i]));


            }
        }

        System.out.println(maxValCapacity[itemValues.length][knapsackCapacity]);





    }


    public static void main(String[] args) {
            knapsack(5);
    }

}
