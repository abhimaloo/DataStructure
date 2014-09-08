package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhimaloo on 9/7/14.
 */
public class LongestSubstringWithoutRepeatingCharacter{
    public static String s = "abcabcbb";

    /**
     * trick is to keep a visited HashMap which records every character we have visited with index as its value
     * Now iterate on input array left to right and maintain the current length of non repeated substring  -
     * now for every index check if you have already visited this character or not -
     * a) if already visited, try to find out whether we have counted the this element in our current length or not. If not increment the current length
     * if this visited character has been counted in the current length, adjust the current length to be  = i - visitedIndex .. before doing so update the maxLength
     * b) if not visited increment the current length by 1
     *
     * finally record the element as visited.
     * @param input
     * @return
     */
    public static int longestSubstringWithoutRepeatingCharacter(char[] input) {
        // this map will keep the mapping of visited character and ints index
        Map<Character, Integer> visited = new HashMap<>();
        // set the current length of NRSubstring
        int currentLength = 1;
        // put the first element in it
        visited.put(new Character(input[0]), 0);
        //initialize the max length
        int maxLength = 0;
        //iterate from left to right
        for( int i = 1; i< input.length; i++) {
            // if visited array contains the character
            if(visited.containsKey(new Character(input[i]))) {
                //check if character was included in the current length or not
                if(visited.get(new Character(input[i])) < i - currentLength) {
                    // if not increment the current length
                    currentLength ++;
                } else {
                    // if character was included in current length
                    //update the maxLength first
                    if(maxLength < currentLength) {
                        maxLength = currentLength;
                    }

                     // now adjust the current length
                    currentLength = i - visited.get(new Character(input[i]));
                }
            } else {
                // if visisted does not contains that key , increment the current length
                currentLength ++;
            }
            // mark the element as visited
            visited.put(new Character(input[i]), i);
        }

        // update the max length if necessary
        if(maxLength < currentLength) {
            maxLength = currentLength;
        }

       // return the max length
       return maxLength;

    }

    public static void main(String[] args) {
        System.out.println(longestSubstringWithoutRepeatingCharacter(s.toCharArray()));
    }

}
