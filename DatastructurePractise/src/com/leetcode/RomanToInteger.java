package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhimaloo on 9/8/14.
 */
public class RomanToInteger {

    public static String roman = "IX";

    /**
     * Very slick solution -
     * Put main roman numbers in a hashMap
     * I - 1
     * V - 5
     * X -10
     * L - 50
     * C - 100
     * D - 500
     * M - 1000
     *
     * Now one by one go throught the roman string left to right
     * get the value of ith character in a variable called val from hashMap
     * check if i is was the last element or i+1th element is bigger than val
     * if yes add the val to the num
     * else
     * subtract the value from num
     *
     * finally return num
     *
     *
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        Map<Character, Integer> romans = new HashMap<Character, Integer>();
        romans.put('I', 1);
        romans.put('V', 5);
        romans.put('X', 10);
        romans.put('L', 50);
        romans.put('C', 100);
        romans.put('D', 500);
        romans.put('M', 1000);
        char[] cs = s.toCharArray();
        int num = 0;
        int val;
        // iterating on roman string left to right
        for (int i = 0; i < cs.length; i++) {
            // get the value for ith character
            val = romans.get(cs[i]);
            // check if this is the last character or the string or i+1th strong is lesser than value (lesser case is for XII )
            if (i == cs.length - 1 || romans.get(cs[i + 1]) <= val) {
                // add the value to the number
                num += val;
            } else {
                //otherwise subtract the number from value ex( IV case which is 4 so -1 +5 = 4)
                num -= val;
            }
        }

        //return the endResult
        return num;
    }


    public static void main(String[] args) {
        System.out.println(romanToInt(roman));
    }
}
