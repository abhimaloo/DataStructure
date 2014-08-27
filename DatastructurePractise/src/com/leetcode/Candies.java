package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * There are N children standing in a line. Each child is assigned a rating value.

 You are giving candies to these children subjected to the following requirements:

 Each child must have at least one candy.
 Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?

 https://oj.leetcode.com/problems/candy/
 * Created by abhimaloo on 8/25/14.
 */
public class Candies {

    public static int [] ratings = {6,7,1,2,2,2,5};

    //very cool trick .. follow the underwritten steps -
    // 1) create a position array with position[i] = i;
    // 2) sort the position array according to the ratings
    //3) now iterate through position array from left to right which means position with smallest rating to highest rating
    //4) check if rating of ith element is greater than i-1 position . If yes take the maximum of 1 and
    // candies assigned to i-1th positon and increment it by 1 and assign it to ith member.

    //5) Do similar stuff for checking rating of i with i+1th element . assign to i the max between 1 and candies[i+1] + 1
    //6) keep a runnning count and return count at last.

    public static int distribute(final int[] ratings) {
        List<Integer> position = new ArrayList<>(ratings.length);
        // position array with position[i] = i
        for( int i = 0; i< ratings.length; i++) {
            position.add(i, i);
        }

        // sort it according to the ratings array
        Collections.sort(position, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(ratings[o1], ratings[o2]);
            }
        });

        // initialize the candies array
        int[] candies = new int[ratings.length];
        int count = 0;
        //iterate through position array from left to right
        for( int i : position) {
            // default candy value
            int c = 1;
            // if rating of i is bigger than i -1th rating
            if (i > 0 && ratings[i] > ratings[i-1]) {
                // give him one more candy among the max of any smaller child
                c = Math.max(c, candies[i-1]+1);
            }
            // if rating of i is more than i+1 element
            if (i < ratings.length - 1 && ratings[i] > ratings[i+1]) {
                // give him one more candy among the max of any smaller
                c = Math.max(c, candies[i+1]+1);
            }
            candies[i] = c;
            // keep a running count of candy
            count += c;
        }

        return count;

    }

    public static void main(String[] args) {
        distribute(ratings);
    }
}
