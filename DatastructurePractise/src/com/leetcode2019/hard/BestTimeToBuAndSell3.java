package com.leetcode2019.hard;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeToBuAndSell3 {
    public static int maxProfit(int[] prices) {
        int firstCost = Integer.MAX_VALUE;
        int firstProfit = 0;
        int secondCost = Integer.MAX_VALUE;
        int secondProfit = 0;

        for (int price : prices) {
            firstCost = Math.min(firstCost, price); // find the minimum cost for first buy.
            firstProfit = Math.max(firstProfit, price - firstCost);  // look at today's price and see if you can make profit
            secondCost = Math.min(secondCost, price - firstProfit);  // now look at the date where you can buy second stock with your profit at minimum rate
            secondProfit = Math.max(secondProfit, price - secondCost); // look for a date to sell it in maximum profit
        }

        return secondProfit;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfit(prices));
    }
}
