package com.leetcode;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
 However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * Created by abhimaloo on 9/24/14.
 */
public class BestTimeToBuyAndSellStock2 {
    public static int[] ticker = {100,80,120,30,90,500};

    public static int maxProfit(int[] ticker) {
        int maxProfit = 0;
        for(int i=1; i<ticker.length; i++) {
            if(ticker[i] > ticker[i-1]){
                maxProfit+= ticker[i] - ticker[i-1];
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(ticker));
    }
}
