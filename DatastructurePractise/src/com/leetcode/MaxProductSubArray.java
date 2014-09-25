package com.leetcode;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.

 For example, given the array [2,3,-2,4],
 the contiguous subarray [2,3] has the largest product = 6.

 https://oj.leetcode.com/problems/maximum-product-subarray/
 * Created by abhimaloo on 9/24/14.
 */
public class MaxProductSubArray {

    public static int [] input = {-2, 4, 0, 3, -1, 8, -1};

    public static void maxProductSubarray(int[] input) {

        int[] min = new int[input.length];
        int[] max = new int[input.length];
        int maxstart = 0;
        int maxend = 0;
        int minstart = 0;

        int left = 0;
        int right = 0;
        int sum = min[0] = max[0] = input[0];
        int maxProduct = Integer.MIN_VALUE;

        for( int i = 1; i< input.length; i++) {

           // max[i] = Math.max(Math.max(input[i] * max[i-1], input[i]), min[i-1] * input[i]);
            if(input[i]* max[i-1] > input[i] && input[i]* max[i-1] > min[i-1]* input[i]) {
                maxend = i;
                max[i] = input[i]* max[i-1];
            } else if(min[i-1]* input[i] > input[i] && min[i-1]* input[i] > input[i]* max[i-1]) {
                maxstart = minstart;
                maxend = i;
                max[i] = min[i-1]* input[i];
            } else if(input[i] >= max[i-1]* input[i] && input[i] >= min[i-1]* input[i]) {
                maxstart = i;
                maxend = i;
                max[i] = input[i];
            }


            //min[i] = Math.min(Math.min(input[i] * max[i - 1], input[i]), min[i-1] * input[i]);
            /// cases for min
            if(input[i]* max[i-1] < input[i] && input[i]* max[i-1] <= min[i-1]* input[i]) {
                minstart = maxstart;

                min[i] = input[i]* max[i-1];
            } else if(min[i-1]* input[i] < input[i] && min[i-1]* input[i] < input[i]* max[i-1]) {

                min[i] = min[i-1]* input[i];
            } else if(input[i] <= max[i-1]* input[i] && input[i] <= min[i-1]* input[i]) {
                minstart = i;

                min[i] =  input[i];
            }



            if(max[i] > maxProduct) {
                maxProduct = max[i];
                left = maxstart;
                right = maxend;
            }

        }

        System.out.println(maxProduct);
        System.out.println(left + ":"+ right);


    }

    public static void main(String[] args) {
        maxProductSubarray(input);
    }

}
