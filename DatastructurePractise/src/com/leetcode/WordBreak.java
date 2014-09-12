package com.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by abhimaloo on 8/23/14.
 */
public class WordBreak {

    public static String s = "catsanddog";
    public static Set<String> dict = new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));

    /**
     * Trick is to use memoization.
     * for every index check if a word in the dictionary starts from their and contained in the input string.
     * record the end position of the word in a boolean array.
     * @param s
     * @param dict
     * @return
     */

    public static boolean wordBreak(String s, Set<String> dict) {
        boolean[] isWord = new boolean[s.length()+1];


        isWord[0] = true;

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
                    isWord[end] = true;

                }

            }

        }

        return isWord[s.length()];

    }

    public static void main(String[] args) {
        System.out.println(wordBreak(s, dict));
    }

}
