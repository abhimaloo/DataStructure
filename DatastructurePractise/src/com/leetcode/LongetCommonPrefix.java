package com.leetcode;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * https://oj.leetcode.com/problems/longest-common-prefix/
 * Created by abhimaloo on 9/8/14.
 */
public class LongetCommonPrefix {

    public static String[] strs = {"abcd", "abcd", "abcd"};

    /**
     * Very slick implementation of Longest Common Prefix. It can easily be done by Trie tree.
     * lets use our method -
     * get the first string into s variable -
     * iterate over all the characters of s one by one
     * inside run a loop on all the remaining strings in the array and check if their match the correspoinding ith element character or not
     * in case of no macth return the substring of s from o to i (i excluded)
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        // save first string
        String s = strs[0];
        // iterate over all the characters of this string
        for(int i = 0; i< s.length(); i++) {
            // hold that character
            char ch = s.charAt(i);
            // iterate over all the remianing strings in the array
            for( int j =1; j< strs.length; j++) {
                // if any of the string does not contains ith character or it does not match .. return the string  0 to i-1
                if(i >= strs[j].length() || strs[j].charAt(i) != ch) {
                    return s.substring(0, i);
                }
            }
        }

        return s;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(strs));
    }
}
