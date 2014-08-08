package com.interview.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given a text txt[0..n-1] and a pattern pat[0..m-1],
 * write a function search(char pat[], char txt[]) that prints all occurrences of pat[] in txt[].
 * You may assume that n > m.
 * Input - text - "@#BABCANXBABCCBABACBABCA"  and pattern - "BABCA"
 * Output - 2, 19 (index at which pattern can be found)
 * Created by abhimaloo on 7/31/14.
 */
public class KMPPatternSearch {

    public static String text = "@#BABCANXBABCCBABACBABCA";
    // Only characters allowed in pattern..no special chars
    public static String pattern = "BABCA";

    /**
     * ALgo : https://www.youtube.com/watch?v=iZ93Unvxwtw
     * trick is to precompute the DFA (Deterministic Finite Automata) according to KMP algo
     * now go through each character of the data and check for the sliding window of pattern.
     * No need to rebound this time since DFA has the states for unmatched occurings as well.
     * @param text
     * @param pattern
     * @return
     */
    public static List<Integer> search(String text, String pattern){
        //only searches for upper case letters
        text = text.toUpperCase();
        pattern = pattern.toUpperCase();

        List<Integer> response = new ArrayList<>();
        HashSet<Character> dedup = new HashSet<>();
        for( int i = 0; i< pattern.length(); i++){
            if(!dedup.contains(pattern.charAt(i))){
                dedup.add(pattern.charAt(i));
            }
        }

        int[][] dfa = buildDFA(pattern, dedup);
        int i=0;
        //this loop is to make sure we keep finding more than one match
        while(i<text.length()){
            int j=0;
            for( ; i<text.length() && j < pattern.length() ; i++){
                //keep looking for the next step.. make sure the chartacter in text is a part of pattern
                if(dedup.contains(text.charAt(i))){
                    j = dfa[text.charAt(i) - 'A'][j];
                }

            }
            //if j has reached the pattern length or last state .. means there is a match
            if(j == pattern.length()){
                //i-pattern.length() will give the start index from where the match begun
                response.add(i-pattern.length());
            }
        }

        return response;

    }

    /**
     ALgo : https://www.youtube.com/watch?v=iZ93Unvxwtw
     */
    private static int[][] buildDFA(String pattern,  HashSet<Character> dedup){

        int[][] dfa = new int[26][pattern.length()];

        dfa[pattern.charAt(0) - 'A'][0] = 1;
        int x =0;
        for( int j=1; j<pattern.length(); j++){
            //set all the unmatching bits
            for(Character c: dedup){
                dfa[c - 'A'][j] = dfa[c -'A'][x];
            }
            //set the matching bit
            dfa[pattern.charAt(j) - 'A' ][j] = j+1;

            x = dfa[pattern.charAt(j) - 'A'][x];
        }

        return dfa;
    }

    public static void main(String[] args) {
        for(Integer index : search(text, pattern)) {
            System.out.println("Pattern matched at index :"+ index);
        }
    }

}
