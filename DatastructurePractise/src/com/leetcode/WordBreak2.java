package com.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * For example, given
 s = "catsanddog",
 dict = ["cat", "cats", "and", "sand", "dog"].

 A solution is ["cats and dog", "cat sand dog"].
 *
 * Created by abhimaloo on 8/25/14.
 */
public class WordBreak2 {

    public static String s = "catsanddog";
    public static Set<String> dict = new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));

    /**
     * Trick is to use memoization .
     * for every index check if a word in the dictionary starts from their and contained in the input string.
     * record the end position of the word in a boolean array.
     * @param s
     * @param dict
     * @return
     */

    public static void wordBreak(String s, Set<String> dict) {
        boolean[] isWord = new boolean[s.length()+1];
        int[] revPointer = new int[s.length()+1];

        isWord[0] = true;
        // add the first index as the front pointer for 0th index
        //frontPointer.put(0, new ArrayList<Integer>());
        //frontPointer.get(0).add(1);

        for( int i = 0; i< s.length(); i++) {

            // start from the matching word
            if(!isWord[i]) {
                continue;
            }

            for( String word : dict) {
                int end = i + word.length();

                if(end > s.length()) {
                    continue;
                }

                if(isWord[end]) {
                    continue;
                }

                if(s.substring(i, end).equals(word)) {
                    revPointer[end] = i;
                    isWord[end] = true;

                }

            }

        }


    }

    public static void main(String[] args) {
        wordBreak(s, dict);
    }

}
