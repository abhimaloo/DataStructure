package com.interview.string;

/**
 * Given a text txt[0..n-1] and a pattern pat[0..m-1],
 * write a function search(char pat[], char txt[]) that
 * prints all occurrences of pat[] in txt[]. You may assume that n > m.
 * Input -
 * txt[] =  "THIS IS A TEST TEXT"
 * pat[] = "TEST"
 * Output - Pattern found at index 10
 *
 *
 * Created by abhimaloo on 7/31/14.
 */
public class NaivePatternSearch {
    public static String text = "THIS IS A TEST TEXT";
    public static String pattern = "TEST";

    /**
     * Trick is to consider each index as start of the pattern and run the patterm window
     * If found a match print the start index
     * @param text
     * @param pattern
     */
    public static void patternMatch(String text, String pattern){
        // considering each index as pattern starting point
        for(int i =0; i<text.length()-pattern.length()-1; i++ ){

            int j = 0;
            for(; j<pattern.length(); j++) {
                if(text.charAt(i+j) != pattern.charAt(j)){
                    break;
                }
            }
             // if complete pattern has matched .. then j would have become equals to pattern size
            if(j == pattern.length()){
                System.out.println("Found Match at index : "+ i);
            }
        }

    }

    public static void main(String[] args) {
        patternMatch(text,pattern);
    }
}
