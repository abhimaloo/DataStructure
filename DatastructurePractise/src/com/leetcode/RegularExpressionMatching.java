package com.leetcode;

/**
 * Implement regular expression matching with support for '?' and '*'.
 *
 * https://oj.leetcode.com/problems/regular-expression-matching/
 * Created by abhimaloo on 9/8/14.
 */
public class RegularExpressionMatching {
    public static String s1 = "geeks";
    public static String s2 = "g*ks";

    /**
     * trick is commented
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isMatch(String s1, String s2) {
        int i = 0;
        int j = 0;
        // iterate while both i and j are within their limits
        while(i < s1.length() && j < s2.length()) {
            // check if s2 has * at its index
            if(s2.charAt(j) == '*') {
                // try finding the element in s2 which is not *
                while(s2.charAt(j) == '*') {
                    j++;
                }
                // it means pattern finished in *, hence mark everything matched and quit
                if(j == s2.length()) {
                   return true;
                }

                //now try finding the matching element in s1 which equals to nonStar element
                while(i < s1.length() && s1.charAt(i) != s2.charAt(j)) {
                    i++;
                }
                // if i has reached the end of the first string that means there is no matching character
                if(i == s1.length()) {
                    return false;
                }

                continue;

            } else {
                // if the character mismatches and the s2's character is not ? .. then return false;
                if(s1.charAt(i) != s2.charAt(j) && s2.charAt(j) != '?') {
                    break;
                }

                i++; // increment i
                j++; // increment j
                // check if BOTH I AND J REACHED THEIR LIMITS RETURN TRUE
                if(i == s1.length() && j == s2.length()) {
                    return true;
                }

            }

        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(isMatch(s1,s2));
    }

}
