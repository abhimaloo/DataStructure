package com.leetcode2019.easy;

/**
 * https://leetcode.com/problems/reverse-integer/
 * Given a 32-bit signed integer, reverse digits of an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: 123
 * Output: 321
 * Example 2:
 * <p>
 * Input: -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {
    public static int reverse(int x) {
        long reverse = 0;  // declared as "long" to contain the value greater than 2^31 -1
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x = x * -1;
        }

        while (x > 0) {
            reverse = (reverse * 10) + (x % 10);
            x = x / 10;
            if (reverse > Integer.MAX_VALUE) {
                return 0;
            }

        }

        return isNegative ? (int) (-1 * reverse) : (int) (reverse);
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }
}
