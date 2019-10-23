package com.leetcode2019.medium;

import java.util.Arrays;

/*
https://leetcode.com/problems/coin-change/
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        if (amount <= 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        Arrays.sort(coins);
        for (int i = 1; i <= amount; i++) {
            // find the maximum coin number <= amount
            for (int j = coins.length - 1; j >= 0; j--) {
                if (i >= coins[j] && dp[i - coins[j]] != -1) {
                    if (dp[i] == -1) {
                        dp[i] = 1 + dp[i - coins[j]];
                    } else {
                        dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                    }
                }
            }
        }

        return dp[amount];
    }

    public static int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        if (amount <= 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange2(new int[]{2}, 3));
    }
}
