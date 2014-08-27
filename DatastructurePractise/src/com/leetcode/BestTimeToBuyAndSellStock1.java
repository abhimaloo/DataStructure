package com.leetcode;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

 If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

 https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * Created by abhimaloo on 8/27/14.
 */
public class BestTimeToBuyAndSellStock1 {

    public static int[] ticker = {100,80,120,30,90,500};

    //trick is to find local minima and then find local maxima right away.. you will buy on local minima and sell on local maxima
    public static int buyAndSell(int[] ticker) {

        int maxProfit = 0;
        for( int i = 1; i< ticker.length; i++) {

            while( i < ticker.length && ticker[i] < ticker[i-1]) {
                i++;
            }

            i--;
            System.out.println("Buy stock Now :"+ i +"th day" );
            int buy = ticker[i];
            i++;
            while(i < ticker.length && ticker[i] > ticker[i-1]) {
                i++;
            }

            i--;
            System.out.println("Sell stock Now :"+ i+"th day");
            maxProfit += ticker[i] - buy;

        }

        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(buyAndSell(ticker));
    }
}
