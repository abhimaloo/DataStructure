package com.interview.arrays;

import java.util.HashSet;

/**
 * Given an integer array and a positive integer k, count all distinct pairs with difference equal to k.
 * Input: arr[] = {1, 5, 3, 4, 2}, k = 3
 * Output: 2
 * There are 2 pairs with difference 3, the pairs are {1, 4} and {5, 2}
 * Created by abhishekm787 on 7/17/14.
 */
public class DistinctPairWithDifferenceEqualsToK {

    public static int[] input = {1, 5, 3, 4, 2};


    /**
     * trick is to use hashset
     * keep inserting values in the hashset
     * while inserting check whether it contains an element
     * which is currentElement + k or currentElement - k
     * if you find one delete that element and increment the counter
     * @param input
     * @param k
     * @return
     */
    public static int countDistinctPair(int[] input, int k){
        HashSet<Integer> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            if(set.contains(input[i]+k)){
                count++;
                set.remove(input[i] + k);
            } else if(set.contains(input[i]-k)){
                count++;
                set.remove(input[i]-k);
            } else {
                set.add(input[i]);
            }

        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countDistinctPair(input, 3));
    }

}
