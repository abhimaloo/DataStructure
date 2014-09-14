package com.leetcode;

/**
 * Implement pow(x, n).
 *
 * https://oj.leetcode.com/problems/powx-n/
 * Created by abhimaloo on 9/11/14.
 */
public class Pow {
    public static double x = 2.0;
    public static int n = 3;

    // Complexity of the solution : log(n)
    public static double pow(double x, int n) {
        //edge cases : o raised to power anything is 0
        if(x == 0) {
            return 0;
        }
        // anything raised to power 0 is 1
        if(n == 0) {
            return 1.0;
        }

        //if x is positive call the power function
        if(x >0) {
            return power(x, n);
        } else {
            // return 1/power() for negative powers
            return 1/power(x, n);
        }

    }

    private static double power(double x, int n) {
        // base case
        if(n == 0) {
            return 1.0;
        }

        // recursively call power with halfing the power
        double res = power(x, n/2);

        // if n was even
        if(n%2 == 0) {
            return res * res;
        } else {
            // n was odd
            return res * res * x;
        }

    }

    public static void main(String[] args) {
        System.out.println(pow(x, n));
    }
}
