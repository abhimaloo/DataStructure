package com.leetcode2019.easy;

public class Pow {
    /*
    lets implement power in two ways; this is recursive way ot implement power for positive and negative cases
     */
    public static double pow(int a, int b) {
        if (a == 0) return 0;
        boolean powerNegative = false;
        boolean numNegative = false;
        if (b < 0) powerNegative = true;
        if (a < 0) numNegative = true;

        double pow = power(Math.abs(a), Math.abs(b));
        pow = numNegative ? (Math.abs(b) % 2 == 0 ? pow : pow * -1) : pow;
        return powerNegative ? 1 / pow : pow;
    }

    public static double power(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 0) {
            return power(a, b / 2) * power(a, b / 2);
        } else {
            return a * power(a, b - 1);
        }
    }


    public static void main(String[] args) {
        System.out.println(pow(-3, -3));
    }
}
