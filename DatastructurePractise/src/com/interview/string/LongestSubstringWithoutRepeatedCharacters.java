package com.interview.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substrings without repeating characters for “ABDEFGABEF” are “BDEFGA” and “DEFGAB”, with length 6
 *
 * http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
 * Created by abhishekm787 on 8/7/14.
 */
public class LongestSubstringWithoutRepeatedCharacters {
    public static String input = "ABDEFGABEF";

    public static int findLongestSubstring(String input) {
        //this keeps the map of character and its index which has been vosoted
        Map<Character,Integer> visited = new HashMap<>();
        int maxLength = Integer.MIN_VALUE;
        int currentLength = 1;
        //visit first element
        visited.put(input.charAt(0), 0);
        // iterate from left to right
        for( int i=1; i< input.length(); i++) {
            //if character has already been visited ..there are two cases
            if(visited.containsKey(input.charAt(i))){
                //Case 1 -
                //if the element which was visited before was not in current length
                if(visited.get(input.charAt(i)) < i -currentLength){
                    currentLength++;
                } else {
                    //case 2 - element which was visited before is part of current length..
                    // plan to change the current length to the i -prevIndex
                    //before changing record the previous current length if it it was maximum or not
                    if(currentLength > maxLength) {
                        maxLength = currentLength;
                    }
                    //change the current length to be i- prevIndex
                    currentLength = i - visited.get(input.charAt(i));
                }

            } else {
                //if the character does not match any previously visited character ..update the current length
                currentLength ++;
            }
            //mark it visited
            visited.put(input.charAt(i),i);

        }
        // this is for final current length chain.. if after the complete iteration current length is more than maxLength
        //update the maxLength
        if(currentLength > maxLength) {
            maxLength = currentLength;
        }

        return maxLength;

    }

    public static void main(String[] args) {

        System.out.println(findLongestSubstring(input));
    }
}
