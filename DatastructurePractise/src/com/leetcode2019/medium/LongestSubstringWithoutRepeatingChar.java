package com.leetcode2019.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

public class LongestSubstringWithoutRepeatingChar {
    /**
     * Solution: Iterate through the string. Maintain the start index of every substring till it breaks.
     * When it breaks update the max length. finally return the max length seen so far
     */
    public static int findMaxLengthOFSubstring(String input) {
        if (input == null) {
            return 0;
        }

        Map<Character, Integer> lookup = new HashMap<>();
        int max = 0;
        int start = 0;

        for (int i = start; i < input.length(); i++) {
            char c = input.charAt(i);
            Integer index = lookup.get(c);
            // Substring breaker should be part of substring
            if (index != null && index >= start) {
                start = index + 1;
            }
            lookup.put(c, i);

            max = Math.max(max, i - start + 1);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(findMaxLengthOFSubstring("abcabcbb"));
    }
}
