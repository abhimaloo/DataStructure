package com.leetcode;

/**
 * Created by abhimaloo on 9/8/14.
 */
public class AtoI {
    public static String input = "123";

    public static int aToI(String input) {

        int result = 0;
        for( int i=0; i< input.length(); i++) {
            result  = result*10 + Integer.parseInt(String.valueOf(input.charAt(i)));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(aToI(input));
    }
}
