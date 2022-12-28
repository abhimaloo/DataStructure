package com.leetcode2022.blind75.strings;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s, find the length of the longest
substring without repeating characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWihoutRepeatingCharacter {

    /*
    Idea is to keep a dedup list. Keep adding items to the list,
    If you find a duplicate character; trim the list from start to the duplicate charcter;
    record the max length and move forward
     */
    public static int lengthOfLongestSubstring(String input) {
        List<Character> dedupList = new ArrayList<>();
        int maxLength = 0;
        for (int i = 0; i < input.length(); i++) {
            if (dedupList.contains(input.charAt(i))) {
                maxLength = Math.max(maxLength, dedupList.size());
                dedupList = dedupList.subList(dedupList.indexOf(input.charAt(i)) + 1, dedupList.size());
            }
            dedupList.add(input.charAt(i));
        }

        maxLength = Math.max(maxLength, dedupList.size());
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aab"));
    }
}
