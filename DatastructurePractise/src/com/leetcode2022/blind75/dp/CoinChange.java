package com.leetcode2022.blind75.dp;

import java.util.Arrays;

/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.



Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0


Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] remianingAmount = new int[amount + 1];
        Arrays.fill(remianingAmount, -1);
        remianingAmount[0] = 0;

        // sort the coins by its magniture
        Arrays.sort(coins);

        for (int i = 1; i <= amount; i++) {
            // for each amount try finding the biggest coin smaller or equals to the amount
            for (int j = coins.length - 1; j >= 0; j--) {
                if (coins[j] <= i && remianingAmount[i - coins[j]] != -1) {
                    if (remianingAmount[i] == -1) {  //if no coin is selected ; select the coin
                        remianingAmount[i] = 1 + remianingAmount[i - coins[j]];
                    } else { // some coin has been selected lets select more optimal coin
                        remianingAmount[i] = Math.min(remianingAmount[i], 1 + remianingAmount[i - coins[j]]);
                    }
                }
            }
        }

        return remianingAmount[amount];
    }
}
