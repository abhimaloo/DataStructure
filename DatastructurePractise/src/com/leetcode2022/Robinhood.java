package com.leetcode2022;

import java.util.LinkedHashMap;
import java.util.Map;

/*
Overview:

Our goal is to build a simplified version of a real Robinhood system that reads prices from a stream and aggregates those prices into historical datapoints aka candlestick charts. We’re looking for clean code, good naming, testing, etc.

Step 1: Parse Prices

Your input will be a comma-separated string of prices and timestamps in the format price:timestamp e.g.

1:0,3:10,2:12,4:19,5:35 is equivalent to

price: 1, timestamp: 0
price: 3, timestamp: 10
price: 2, timestamp: 12
price: 4, timestamp: 19
price: 5, timestamp: 35

You can assume the input is sorted by timestamp and values are non-negative integers.

Step 2: Aggregate Historical Data from Prices

We calculate historical data across fixed time intervals. In this case, we’re interested in intervals of 10, so the first interval will be [0, 10). For each interval, you’ll build a datapoint with the following values.

Start time
First price
Last price
Max price
Min price

Important: If an interval has no prices, use the previous datapoint’s last price for all prices. If there are no prices and no previous datapoints, skip the interval.

You should return a string formatted as {start,first,last,max,min}. For the prices shown above, the expected datapoints are

{0,1,1,1,1}{10,3,4,4,2}{20,4,4,4,4}{30,5,5,5,5}

[execution time limit] 3 seconds (java)

[input] string prices_to_parse

[output] string
 */
public class Robinhood {
    /*

     */
    public static String solution(String prices_to_parse) { // input stream is sorted price:timestamp; timestamp is sorted and price is non negative
        //output format {start,first,last,max,min}.
        Map<Integer, CandleStick> intervalStore = new LinkedHashMap<>();

        //parse the input stream and save
        String[] quotes = prices_to_parse.split(",");
        int firstInterval = 0;
        int lastInterval = 0;

        for (int i = 0; i < quotes.length; i++) {
            String[] decipheredQuote = quotes[i].split(":");
            int price = Integer.parseInt(decipheredQuote[0]);
            int timestamp = Integer.parseInt(decipheredQuote[1]);

            // 11 /10 = 1  ; 9 /10  = 0
            int bucket = timestamp / 10;


            if (i == 0) {
                firstInterval = bucket;
            }
            if (i == quotes.length - 1) {
                lastInterval = bucket;
            }

            if (intervalStore.containsKey(bucket)) {
                intervalStore.get(bucket).update(price, timestamp);
            } else {
                intervalStore.put(bucket, new CandleStick(price, bucket * 10));
            }
        }


        // output
        StringBuilder result = new StringBuilder();
        for (int i = firstInterval; i <= lastInterval; i++) {
            if (intervalStore.containsKey(i)) {
                result.append(intervalStore.get(i).toString());
            } else {
                CandleStick emptyCandle = new CandleStick(intervalStore.get(i - 1).getLastPrice(), i * 10);
                intervalStore.put(i, emptyCandle);
                result.append(emptyCandle.toString());
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution(("5:40,9:47,2:101,6:103")));
        //System.out.println(solution("3:12,1:15,4:18,1:30,5:40,9:47,2:101,6:103,5:105,3:107,5:108,8:120,9:121,7:122,9:124,3:125,2:126,3:127,8:128,4:129"));
    }

}

class CandleStick {
    private int startTime;
    private int firstPrice;
    private int lastPrice;
    private int maxPrice;
    private int minPrice;

    public CandleStick(int price, int timestamp) {
        //this.startTime = timestamp;
        this.startTime = timestamp;
        this.firstPrice = price;
        this.lastPrice = price;
        this.maxPrice = price;
        this.minPrice = price;
    }

    public void update(int price, int timestamp) {
        this.lastPrice = price;
        this.maxPrice = Math.max(this.maxPrice, price);
        this.minPrice = Math.min(this.minPrice, price);
    }

    public int getLastPrice() {
        return lastPrice;
    }

    @Override
    public String toString() {
        return String.format("{%d,%d,%d,%d,%d}", startTime, firstPrice, lastPrice, maxPrice, minPrice);
    }
}
