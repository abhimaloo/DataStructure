package com.leetcode2019.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/word-break-ii/
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
 */
public class WordBreakII {
    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> resultset = new ArrayList<>();
        dfs(s, 0, wordDict, resultset, "");
        return resultset;
    }

    public static void dfs(String s, int start, List<String> wordDict, List<String> resultSet, String result) {
        if (start == s.length()) {
            resultSet.add(result.substring(1)); // remove the leading space
            return;
        }

        String temp = "";
        while (start < s.length()) {
            temp += s.charAt(start++);
            if (contains(temp, wordDict)) {
                dfs(s, start, wordDict, resultSet, result + " " + temp);
            }
        }
    }

    public static boolean contains(String s, List<String> words) {
        for (String item : words) {
            if (item.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] wordDict = new String[]{"cat", "cats", "and", "sand", "dog"};
        wordBreak("catsanddog", Arrays.asList(wordDict));
    }
}
