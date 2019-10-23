package com.datastructure2019.dp;

public class Knapsack {
    /*
    https://www.youtube.com/watch?v=8LusJS5-AGo&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=2&t=23s
    Good Explanation
     */
    public static int maxValue(int[] values, int[] weights, int capacity) {
        // this array represents the matrix of knapsack capacity and weights
        int[][] dp = new int[weights.length + 1][capacity + 1];
        // if weight is 0 you cannot select any values
        for (int i = 0; i <= weights.length; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i < weights.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i] > j) { // if weight itself is bigger than capacity then we cannot add the item to the bag
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = Math.max(dp[i][j], values[i] + dp[i][j - weights[i]]);
                }
            }
        }

        return dp[weights.length][capacity];
    }

    public static void main(String[] args) {
        int[] itemValues = {1, 5, 7, 8, 2, 3};
        int[] itemWeights = {1, 2, 3, 1, 2, 1};
        System.out.println(maxValue(itemValues, itemWeights, 5));
    }

}
