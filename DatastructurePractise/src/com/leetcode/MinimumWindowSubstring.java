package com.leetcode;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".

 https://oj.leetcode.com/problems/minimum-window-substring/
 * Created by abhimaloo on 9/22/14.
 */
public class MinimumWindowSubstring {
    public static String s = "ADOBECODEBANC";
    public static String t = "ABC";

    public static void findMinimumWindow(String s, String t) {
        char[] needToFind = new char[256];
        char[] hasFound = new char[256];

        for( int i = 0 ; i< t.length(); i++) {
            needToFind[t.charAt(i)] ++;
        }

        // how many elements have been found
        int count = 0;
        int start = 0;
        int end = 0;
        int minstart = -1;
        int minend = -1;
        int minLength = Integer.MAX_VALUE;

        while(end < s.length()) {
            if(needToFind[s.charAt(end)] == 0) {
                end++;
                continue;
            }

            hasFound[s.charAt(end)] ++;

            if(hasFound[s.charAt(end)] <= needToFind[s.charAt(end)]) {
                count ++;
            }

            // we have reached at a window .. now try shrinking it as much as you can without breaking the constraint
            if(count == t.length()) {

                while (hasFound[s.charAt(start)] == 0 || hasFound[s.charAt(start)] > needToFind[s.charAt(start)]) {
                    if(hasFound[s.charAt(start)] > needToFind[s.charAt(start)]) {
                        hasFound[s.charAt(start)] --;
                    }
                    start ++;
                }

                if(end - start+1 < minLength) {
                    minLength = end - start +1;
                    minstart = start;
                    minend = end;
                }

            }

            end ++;
        }

        System.out.println(s.substring(minstart, minend+1));

    }

    public static void main(String[] args) {
        findMinimumWindow(s, t);
    }


}
