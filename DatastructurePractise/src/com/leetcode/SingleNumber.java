package com.leetcode;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Created by abhimaloo on 8/25/14.
 */
public class SingleNumber {
    public static int [] input = {1,2,2,1,3,3,4,4,5};

    public static int findSingleNumber(int[] input) {
        int xor = input[0];
        for( int i = 1; i< input.length; i++) {
            xor ^= input[i];
        }

        return xor;
    }

    public static void main(String[] args) {
        System.out.println(findSingleNumber(input));
    }

}
