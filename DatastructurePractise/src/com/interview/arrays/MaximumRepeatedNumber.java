package com.interview.arrays;

/**
 * Created by abhishekm787 on 7/15/14.
 * Given an array of size n, the array contains numbers in range from 0 to k-1
 * where k is a positive integer and k <= n.
 * Find the maximum repeating number in this array.
 * For example, let k be 10 the given array be arr[] = {1, 2, 2, 2, 0, 2, 0, 2, 3, 8, 0, 9, 2, 3},
 * the maximum repeating number would be 2
 */
public class MaximumRepeatedNumber {
    public static int [] input =  {1, 2, 2, 2, 0, 2, 0, 2, 3, 8, 0, 9, 2, 3};
    public static int k = 10;

    /**
     * Trick is to add 'k' to input[input[i]%k]
     * Whenever a number is repeated it is adding k to a particular index again and again
     * finally find the number with highest value, index of the number will be the value of max repeat number
     * value%10 will return the value itself, hence you are incrementing index equals to value again and again
     * @param input
     */
    public static void findMaxRepeat(int[] input){
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for( int i=0;i<input.length; i++){
            input[input[i] %k] += k;
            if(max < input[input[i] %k]) {
                max = input[input[i] %k];
                maxIndex = input[i] %k;
            }
        }

        System.out.println("Max repeating number is "+ maxIndex);

    }

    public static void main(String[] args) {
        findMaxRepeat(input);
    }

}
