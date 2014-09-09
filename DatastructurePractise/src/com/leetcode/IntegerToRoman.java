package com.leetcode;

/**
 * Given an integer, convert it to a roman numeral.

 Input is guaranteed to be within the range from 1 to 3999.

 https://oj.leetcode.com/problems/integer-to-roman/
 * Created by abhimaloo on 9/8/14.
 */
public class IntegerToRoman {

    public static int number = 12;

    /**
     * Very sleek implementation.
     * Keep an array of symbols and values in decreasing order
     * specially 100 = C
     * 90  = XC
     * 50 = L
     * 40 = XL
     * 10 = X
     * 9  = IX
     * 5 = V
     * 4 = IV
     * 1 = I
     *
     * Now num a for loop of valies from left to right
     * check while value[i] is less than equals ot the number or not
     * append the number as many times the value is smaller and keep decreasing the value from the number every time.
     *
     *
     * @param number
     * @return
     */
    public static String convertToRoman(int number) {

        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder roman = new StringBuilder();
        // run through all the values
        for(int i=0; i< values.length; i++) {
            //while number is greater than or equals to value
            while(number >= values[i]) {
                // append the symbol
                roman.append(symbols[i]);
                //subtract the value from the number and check again
                number -= values[i];
            }
        }

        //return the converted string
        return roman.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToRoman(number));
    }
}
