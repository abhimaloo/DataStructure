package com.facebook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 For example, given
 s = "leetcode",
 dict = ["leet", "code"].

 Return true because "leetcode" can be segmented as "leet code".
 * Created by abhimaloo on 9/26/14.
 */
public class WordBreak {

    public static String s = "catsanddog";
    public static Set<String> dict = new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));

    public static boolean wordBreak(String s, Set<String> dict) {

        boolean[] isWord = new boolean[s.length()+1];
        isWord[0] = true;

        for( int i = 0; i< s.length(); i++) {

            if(!isWord[i]) {
                continue;
            }

            for(String word : dict) {
                int end = i + word.length();
                if(end > s.length()) {
                    continue;
                }

                if(isWord[end]) {
                    continue;
                }

                if(s.substring(i,end).equals(word)) {
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
