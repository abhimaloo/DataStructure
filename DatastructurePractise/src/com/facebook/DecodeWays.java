package com.facebook;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class DecodeWays {



    public static int numDecodings(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (s == null || s.equals("")) return 0;
        char[] cc = s.toCharArray(); //conver to char array
        int n = s.length(); //number of chars
        int numEn[] = new int[n+1];
        numEn[0] = 1;
        if (cc[0] >= '1' && cc[0] <= '9') numEn[1] = 1;
        else return 0;

        for (int i = 1; i < n; i ++) {
            if (cc[i] >= '1' && cc[i] <= '9') {
                if (s.substring(i-1,i+1).compareTo("10") >= 0
                        && s.substring(i-1,i+1).compareTo("26") <= 0) {
                    numEn[i+1] = numEn[i] + numEn[i-1];
                } else {
                    numEn[i+1] = numEn[i];
                }
            } else {
                if (s.substring(i-1,i+1).compareTo("10") >= 0
                        && s.substring(i-1,i+1).compareTo("26") <= 0) {
                    numEn[i+1] = numEn[i-1];
                } else {
                    return 0;
                }
            }
        }

        return numEn[n];
    }


    /**
     * There is a DP explanation of it  ..
     * Lets assume a code 123 .. Its a code of length 4
     * lets try and see how we could have found the ways to decode the same string with length 0, 1, 2, 3 gradually.
     * we want to find the connection between them ..
     * ways to decode an empty string  "" of length 0 = 1
     * ways to decode a string with one letter "1" will be  - 1
     *
     * Now DP kicks in
     *
     * ways to decode a string with length 2 - "12"   - if char at i and i-1 forms a valid code .. then ways[i] would be ways[i-1]+ways[i-2]
     *
     * ways to decode a string with length 2 - "123" - Since adding 3 to the string "12" generate "23" as new valid code
     *
     *
     * @param code
     * @return
     */
    /**
     * LeetCode
     * Decode Ways
     *
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:

     'A' -> 1
     'B' -> 2
     ...
     'Z' -> 26
     Given an encoded message containing digits, determine the total number of ways to decode it.

     For example,
     Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

     The number of ways decoding "12" is 2.

     * solution by zingshow on June 27, 2013
     * two algorithms to solve this problem
     * 1. graph + depth-first search (DFS) + recursion
     * 2. dynamic programming (DP) (current code). it is very similar to "Climbing
     * Stairs" question. Find the solution to sub-problem first and grow sub-problem.
     *
     * algorithm 2:
     * 1) find the number of ways to decode the first char: 1 if the first char
     * (char 1) is between "1" and "9", 0 if else (and return 0).
     * we can assume a virtual char (char 0)
     * is before the first char and the number of ways to decode it is 1.
     * 2) decode the string until second char (char 2)
     * and the rest with four situations:
     *
     * a) if char n is ("1" to "9") and char n-1 + char n is ("10" to "26"),
     * the number of ways to decode
     * the string until char n is the sum of the number of ways of
     * decoding the string until char n-1 and decoding the string until char n-2:
     * for string until char 2, it is number of ways for char 0
     * plus number of ways for char 1;
     *
     * b) if char n is ("1" to "9") but char n-1 + char n is not ("10" to "26"),
     * the number of ways to decode the string until char n
     * is equal to the number of ways to decode the string until char n-1:
     * for string until char 2, it is equal to the number of ways for char 1;
     *
     * c) if char n is not ("1" to "9") but char n-1 + char n is ("10" to "26"),
     * the number of ways to decode the string until char n
     * is equal to the number of ways to decode the string until char n-2:
     * for string until char 2, it is equal to number of ways for char 0;
     *
     * d) if neither char n is ("1" to "9") nor char n-1 + char n is ("10" to "26"),
     * the number of ways to decode the string until char n is zero and zero can
     * be returned immediately
     *
     * comments: DP has less time and space complexity O(1).
     *
     */
    public static int decodeWays(String code) {

        //check if code is null or empty.. we cannot decode empty code so return 0 as answer
        if(code == null || code.length() == 0){
            return 0;
        }
        // this is to keep the DP subproblem results
        // ways[i] will be count of ways by which we can decode string of length i
        int[] ways = new int[code.length()+1];
        // assume there is a way to decode 0 length code
        ways[0] = 1;
        // check the first character od the code, if its between 1 to 9 inclusive, it means we can decode the first char is one way
        if(code.charAt(0) >= '1' && code.charAt(0) <= '9') {
            ways[1] = 1;
        } else {
            return 0;  // any value like alphabets character or even 0 will return 0, since it cannot be decoded
        }

        // check for all the codes greater than equals to length 2
        for(int i = 1; i< code.length(); i++) {
            //if ith letter is between 1 and 9
            if(code.charAt(i) >= '1' && code.charAt(i) <= '9') {
                // check if it also makes a valid code when appended with i-1 character ..
                if(code.substring(i-1, i+1).compareTo("10") >= 0 && code.substring(i-1, i+1).compareTo("26") <= 0) {
                    // if it does add ways to decode i-1 and i-2 chars
                    ways[i+1] = ways[i] + ways[i-1];
                } else {
                    // it could be starting a new code like '9' in case of 29 or '7' in case of '27'
                    ways[i+1] = ways[i];
                }
            } else {
                // it could be '0' or somethig else.. if its zero is it making a valid composite code like '10' or '20'
                if(code.substring(i-1, i+1).compareTo("10") >= 0 && code.substring(i-1, i+1).compareTo("26") <= 0) {
                    // if it is assign the ways of i-1 length string
                    ways[i+1] = ways[i-1];
                } else {
                    // if it is not zero .. but other garbage special character etc.. return 0 right from here
                    return 0;
                }

            }

        }

        return ways[code.length()];
    }





    public static void main(String[] args) {
        System.out.println(decodeWays("1203"));
    }


}
