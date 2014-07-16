package com.interview.arrays;

/**
 * Created by abhishekm787 on 7/15/14.
 *The cost of a stock on each day is given in an array,
 * find the max profit that you can make by buying and selling in those days.
 * For example, if the given array is {100, 180, 260, 310, 40, 535, 695},
 * the maximum profit can earned by buying on day 0, selling on day 3.
 * Again buy on day 4 and sell on day 6.
 */
public class StockBuySell {
    public static int [] input = {100, 180, 260, 310, 40, 535, 695} ;

    public static void buySell(int[] input){
        // find local minima and local maxima till it reaches the end
        int index = 0;
        while(index < input.length-1){

            //find local minima .. make sure you are not going over the limit
            // compare index with index+1
            while(index < input.length -1 && input[index]> input[index+1]){
                index++;
            }

            //if the index reaches end that means there is no good day to buy a stock
            if(index >=input.length-1){
               return ;
            }

            // index is increased so that we can safely compare local maxima later
            System.out.println("Buy Stock at price :"+ input[index++]);

            //find local maxima .. compare index-1 to index since
            while(index< input.length && input[index-1] < input[index]){
                index++;
            }

            System.out.println("Sell stock at price: "+ input[index-1]);

        }


    }


    public static void main(String[] args) {
        buySell(input);
    }
}
