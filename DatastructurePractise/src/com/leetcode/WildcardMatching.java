package com.leetcode;

/**
 * Implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "*") → true
 isMatch("aa", "a*") → true
 isMatch("ab", "?*") → true
 isMatch("aab", "c*a*b") → false

 https://oj.leetcode.com/problems/wildcard-matching/
 * Created by abhimaloo on 8/28/14.
 */
public class WildcardMatching {


    /**
     * trick is to move pointer both the pointers one by one .. this problem is about matching the complete string with the pattern not finding the pattern in the string
     * till both the pointers are less than their respective lengths - Do certain steps -
     * a) check if current pointer at pattern contains "*" as wildcard character. If yes, try moving pointer to the next character in pattern which is not equals to *
     * Once you reach there, check if pointer has reached to the length or not. If yes, return true, which means last character of pattern was * hence accept the match wihtout checking further.
     * If pointer has not reached last element, try to find the first element in haysack which matches with the current non (*) pointer. While doing this if haysack has reached to the length return false
     * which means haysack does not contain any character which matches the current pointer of pattern.
     *
     * b) check if current pointer does not contains '*'. In this case check return false(or break) if haysack pointer and pattern pointer points to two different chracters and pattern pointer is not pointing to '?'
     * increase the haysack pointer and pattern pointer by 1. if both of them has reached to their lengths return true
     *
     *
     * @param haysack
     * @param pattern
     * @return
     */
    public static boolean isMatch(String haysack, String pattern) {
        int haysackIndex = 0;
        int patternIndex = 0;
        // while both pointers are less then their respective length
        while (haysackIndex < haysack.length() && patternIndex < pattern.length()) {
            // if patttern pointer points to '*'
            if(pattern.charAt(patternIndex) == '*') {
                // increment pattern pointer to point to go to next non * element
                while(patternIndex < pattern.length() && pattern.charAt(patternIndex) == '*') {
                    patternIndex ++;
                }

                // if pattern ends in * .. accept everything in source
                if(patternIndex == pattern.length()) {
                    return true;
                }

                // move haysack pointer to the first character matching pattern pointer
                while(haysackIndex < haysack.length() && haysack.charAt(haysackIndex) != pattern.charAt(patternIndex)) {
                    haysackIndex ++;
                }

                // if cant find the matching character in haysack.. return false
                if(haysackIndex == haysack.length()) {
                    return false;
                }
                // continue the loop
                continue;

            } else {
                // if character is not * .. try cmparaing thr haysack pointer and pattern pointer .. if they do not match and patternis not ?
                //break from the loop (whuich means you are going to return false)
                if(haysack.charAt(haysackIndex) != pattern.charAt(patternIndex) && pattern.charAt(patternIndex) != '?') {
                   break;
                }

                //increment both the pointer sicne the match happenned
                haysackIndex ++;
                patternIndex ++;
                // if both the pointer have moved beyond their lengths.. means all the characters have been matched so return true
                if(haysackIndex == haysack.length() && patternIndex == pattern.length()) {
                    return true;
                }

            }
        }

        return false;

    }




    public static void main(String[] args) {
        System.out.println(isMatch("abhi", "a*"));
    }
}
