package com.leetcode;

/**
 * Created by abhimaloo on 8/23/14.
 */
public class ReverseInteger {
    public static int input= 123;

    public static int reverse(int x){
        int result = 0;
        boolean positive = true;
        if(x<0) {
           positive = false;
            x = x * -1;
        }
        while(x > 0) {
            result = result*10 + x %10;
            x = x/10;
        }

       return positive? result : -1* result;
    }

    public static void main(String[] args) {
        System.out.println(reverse(input));
    }

}
