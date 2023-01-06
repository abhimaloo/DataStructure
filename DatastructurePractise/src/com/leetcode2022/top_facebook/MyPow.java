package com.leetcode2022.top_facebook;

/*
Implement pow(x, n), which calculates x raised to the power n (i.e., xn).



Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25


Constraints:

-100.0 < x < 100.0
-231 <= n <= 231-1
n is an integer.
-104 <= xn <= 104
 */
public class MyPow {
    public double myPow(double x, int n) {
        if (n == 0 || x == 1.0) return 1.0;
        boolean powNegative = n < 0;
        n = Math.abs(n);
        double response = pow(x, n);
        return powNegative ? (double) (1 / response) : response;
    }

    public double pow(double x, int n) {
        if (n == 0) return 1;
        double half = pow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return x * half * half;
        }
    }
}
