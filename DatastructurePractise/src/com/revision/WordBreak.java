package com.revision;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

 Return all such possible sentences.

 For example, given
 s = "catsanddog",
 dict = ["cat", "cats", "and", "sand", "dog"].

 A solution is ["cats and dog", "cat sand dog"].
 * Created by abhishekm787 on 9/11/14.
 */
public class WordBreak {

    public static String s = "catsanddog";
    public static String[] dict = {"cat", "cats", "and", "sand", "dog"} ;


    /**
     * Idea is to use dynamic programming ..
     * for string of length x , we can break the word if last element of x is the one where at least one of the dict word finishes
     * there is continuity between word start and finish
     * @param s
     * @param dict
     * @param result
     */
    public static void wordBreak(String s, String[] dict, List<String> result) {
        // keep this boolean array for keeping isWord[j] if a word finishes at index j
        boolean[] isWord = new boolean[s.length() +1];
        Map<Integer, List<Integer>> rev = new HashMap<>();
        // for zero length string , we assume that its a valid word break
        isWord[0] = true;
        rev.put(new Integer(0), new ArrayList<Integer>());



        // go thrrough i  0 to length
        for(int i  = 0; i< s.length(); i++) {

            // go to first matching word, because we want to test continuity.
            if(!isWord[i]) {
                continue;
            }

            // iterate through all the words and check if any of them starts at i
            // if yes mark isWord[end] = true where end would be i + word.length
            for(String word : dict) {

                int end = i + word.length();

                if(end > s.length()) {
                    continue;
                }

                if(s.substring(i, end).equals(word)) {
                    isWord[end] = true;
                    if(!rev.containsKey(new Integer(end))) {
                        rev.put(new Integer(end), new ArrayList<Integer>());
                    }
                    rev.get(new Integer(end)).add(new Integer(i));

                }
            }
        }

    }


    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        wordBreak(s, dict,result);
    }

}
