package com.leetcode;

/**
 * Implement int sqrt(int x).
 Compute and return the square root of x.
 * Created by abhimaloo on 9/18/14.
 */
public class Sqrt {
    public static int sqrt(int a) {
        int high  = a;
        int low = 0;

        while(high-low >1){
            int mid = low + ((high - low)/2);
            if(mid * mid <= a) {
                low = mid;
            } else {
                high = mid;
            }

        }

        return low;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(18));
    }
}
