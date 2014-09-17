package com.crackCode;

/**
 * The Largest Product Continuous Substring(LPCS) problem
 * Created by abhimaloo on 9/16/14.
 */
public class MaximumSubArrayProduct {

    public static double[] input = {-2.5, 4, 0, 3, 0.5, 8, -1};

    public static double findMaxProduct(double[] input) {

        double[] max = new double[input.length];
        double[] min = new double[input.length];
        double maxProduct = min[0] = max[0] = input[0];

        for( int i = 1; i< input.length; i++) {

           max[i] = Math.max(Math.max(max[i-1] * input[i], input[i]), min[i-1]* input[i]);
           min[i] = Math.min(Math.min(max[i - 1] * input[i], input[i]), min[i - 1] * input[i]);
           maxProduct = Math.max(maxProduct, max[i]);

        }

        return maxProduct;
    }

    public static void main(String[] args) {
        System.out.println(findMaxProduct(input));
    }
}
