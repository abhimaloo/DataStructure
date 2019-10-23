package com.leetcode2019.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/most-common-word/
Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.



Example:

Input:
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation:
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"),
and that "hit" isn't the answer even though it occurs more because it is banned.


Note:

1 <= paragraph.length <= 1000.
0 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.
 */
public class MostCommonWord {
    public static String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) return paragraph;
        Set<String> set = new HashSet();
        Map<String, Integer> map = new HashMap();
        for (String s : banned) {
            set.add(s);
        }
        int mostCommon = 0;
        String maxword = null;
        paragraph = paragraph.replaceAll("[!?',;.]", " ").toLowerCase();
        for (String word : paragraph.split(" ")) {
            if (set.contains(word) || word.equals("")) continue;
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
            if (map.get(word) > mostCommon) {
                mostCommon = map.get(word);
                maxword = word;
            }
        }

        return maxword;

    }

    public static void main(String[] args) {
        System.out.println(mostCommonWord("Bob. hit, baLl", new String[]{"bob", "hit"}));
    }
}
