package com.leetcode2022.blind75.dp;

import java.util.Arrays;
import java.util.List;

/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false


Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */
public class WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] isWord = new boolean[s.length() + 1];
        isWord[0] = true;

        for (int i = 0; i < s.length(); i++) {
            // jump to the index where a legal match exist
            if (!isWord[i]) {
                continue;
            }

            for (String word : wordDict) {
                int end = i + word.length();
                // this is to check if the word would not fit the input string length
                if (end > s.length()) {
                    continue;
                }
                // if its already a word then we can skip it
                if (isWord[end]) {
                    continue;
                }

                if (s.substring(i, end).equals(word)) {
                    isWord[end] = true;
                }
            }
        }
        return isWord[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));
    }
}
