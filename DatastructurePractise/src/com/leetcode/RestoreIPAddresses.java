package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 For example:
 Given "25525511135",

 return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

 https://oj.leetcode.com/problems/restore-ip-addresses/
 * Created by abhimaloo on 9/11/14.
 */
public class RestoreIPAddresses {
    public static String s = "25525511122";


    /**
     * Idea is say if the string is 25525511135. NOw you have to find all the valid division into 4 sub groups so that
     * subgroup elements are in the correct range of 0 to 255
     *
     * Solution will require DFS or backtracking
     *
     * we need to process all substrings of length 1 to 3 for a given octet.
     * check if count == 3, which means s contains the element of the last octet.
     * if the contents of s makes a valid number between 0 to 255 , add the temp + s to the result and return from there
     * if octet ==3 and s is not a valid number return from the method.
     *
     *
     * @param s
     * @param temp
     * @param result
     * @param count
     */
    public static void restoreIpAddress(String s ,String temp,List<String> result, int count) {
        // base condition - if count has reached 3 which means s contains the data for lasts octet
        if(count == 3 ){
            // check if s is valid octet data
            if(isValid(s)){
                // if yes; add it to result
                result.add(new String(temp + s));
            }
            // return in either cases
            return;
        }

        // for trying substring of 1 to 3 lengths
        for( int i =1; i<4 ; i++) {
            // find substring of s of length i
            String octet = s.substring(0, i);
            // if its a valid octet, recurse
            if(isValid(octet)) {
                // dfs or recurse by removing the present octet from the main string,
                // add present octet to the temp string and increment the count
                restoreIpAddress(s.substring(i), temp+octet+".", result, count+1);
            }
        }

    }

    private static boolean isValid(String s) {

        if(s.charAt(0) == '0') {
            return s.equals("0");
        }

        boolean response = false;
        try {
            int num = Integer.parseInt(s);
            response = num >=0 && num <256;

        } catch (NumberFormatException nfe) {
        }

        return response;
    }


    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        restoreIpAddress(s, "", result, 0);
        for(String s : result) {
            System.out.println(s);
        }
    }
}
