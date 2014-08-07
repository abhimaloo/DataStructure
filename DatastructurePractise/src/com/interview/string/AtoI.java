package com.interview.string;

/**
 * Created by abhimaloo on 8/6/14.
 */
public class AtoI {
    public static String input = "2145";

    /**
     * cool trick .. start from left to right ..
     * keep multiplying the result by 10 and add the value at the position to the result
     * Ex - "245"  Loop will start from
     * 1) 2
     * b)2 *10 +4 = 24
     * c) 24 * 10 +5 = 245
     *
     * @param input
     * @return
     */
    public static int aToi(String input) {
        int result = 0;
        for( int i=0; i< input.length();i++) {
            if(i==0) {
                result = Integer.parseInt(String.valueOf(input.charAt(i)));
            } else {
                result  = result*10 + Integer.parseInt(String.valueOf(input.charAt(i)));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(aToi(input));
    }
}
