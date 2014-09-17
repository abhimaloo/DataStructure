package com.crackCode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by abhimaloo on 9/16/14.
 */
public class LongestWordMadeByOtherWord {

    public static boolean containsWordsFromDict(String s, String[] dict) {

        boolean[] contains = new boolean[s.length()+1];
        contains[0] = true;
        for( int i = 0; i< s.length(); i++) {

            if(!contains[i]) {
                continue;
            }

            for(String word : dict) {
                if(word.equals(s)) {
                    continue;
                }
                int end = i + word.length();

                if(end > s.length()) {
                    continue;
                }

                if(s.substring(i, end).equals(word)){
                    contains[end] = true;
                }

            }

        }

        return contains[s.length()];

    }

    public static String longestWord(String[] dict) {



        Arrays.sort(dict, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -1* Integer.compare(o1.length(), o2.length());
            }
        });

        for( int i=0; i< dict.length; i++) {
            if(containsWordsFromDict(dict[i],dict)){
                return dict[i];
            }
        }

        return null;

    }

    public static void main(String[] args) {

       String[] dict = { "test" ,  "Tester" ,  "testertest" ,  "Testing" ,
                "Apple" ,  "Seattle" ,  "Banana" ,   "batting" ,  "ngcat" ,
                "batti" , "BAT" ,  "testingtester" ,  "testbattingcat" };

        System.out.println(longestWord(dict));
    }

}
