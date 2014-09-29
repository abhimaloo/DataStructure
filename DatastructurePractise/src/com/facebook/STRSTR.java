package com.facebook;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class STRSTR {

    public static String text = "THIS IS A TEST TEXT";
    public static String pattern = "TEST";

    public static int naivePatternSearch(String text, String pattern) {

        for( int i = 0; i< text.length(); i++) {

            int j = 0;
            for( j = 0; j < pattern.length(); j++) {
                if(text.charAt(i+j) != pattern.charAt(j)) {
                    break;
                }
            }
            if(j == pattern.length()){
                return i;
            }
        }

        return -1;
    }

    public static int kmpStrStr(String text, String pattern) {
        if(text == null || pattern == null) {
            return -1;
        }

        Set<Character> dedup = new HashSet<>();
        // find all unique characters in pattern
        for(char c : pattern.toCharArray()) {
            dedup.add(c);
        }

        int[][] dfa = buildDFA(pattern, dedup);
        int i = 0;
        int j = 0;
        for(;i< text.length() && j < pattern.length(); i++) {

            if(dedup.contains(text.charAt(i))) {
                j = dfa[text.charAt(i) - 'A'][j];
            }

        }

        if(j == pattern.length()){
            return i - pattern.length();
        }
        return -1;
    }

    private static int[][] buildDFA(String pattern, Set<Character> dedup) {
        // only allows small characters
        int[][] dfa = new int [26][pattern.length()];
        int x = 0;
        dfa[pattern.charAt(0) -'A'][0] = 1;
        for( int i = 1; i< pattern.length(); i++) {

            //copy over the states from previous mismatches
            for(char c : dedup) {
                dfa[c - 'A'][i] = dfa[c - 'A'][x];
            }
            // overwrite the matching state
            dfa[pattern.charAt(i) - 'A'][i] = i + 1;
            //update the x
            x = dfa[pattern.charAt(i) - 'A'][x];

        }

        return dfa;

    }


    public static void main(String[] args) {
        System.out.println(naivePatternSearch(text, pattern));
        System.out.println(kmpStrStr(text, pattern));
    }

}
