package com.facebook;

import java.util.*;

/**
 * Created by abhimaloo on 9/27/14.
 */
public class WordLadderII {

    public static String start = "hit";
    public static String end = "cog";
    public static Set<String> dict = new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));

    public static List<List<String>> wordLadder(String start, String end, Set<String> dict) {

        List<List<String>> result = new ArrayList<>();
        Map<String, String> parentMap = new HashMap<>();

        if(start == null || end  == null) {
            return null;
        }

        if(dict == null) {
            if(start.equals(end)) {
                return result;
            } else {
                dict = new HashSet<>();
            }
        }

        dict.add(end);
        parentMap.put(start, null);
        dfs(start, end, start, dict, result, parentMap, Integer.MAX_VALUE);
        return result;
    }

    public static int dfs(String start, String end, String word, Set<String> dict, List<List<String>> result,  Map<String, String> parentMap , int minLength) {

        if(word.equals(end)) {
            ArrayList<String> path = new ArrayList<>();
            String parent = parentMap.get(word);
            path.add(end);
            while(parent != null && !parent.equals(start)){
                path.add(parent);
                parent = parentMap.get(parent);
            }
            path.add(start);
            Collections.reverse(path);
            if(path.size() <= minLength) {
                result.add(path);
                return path.size();
            }
            return minLength;
        }

        for(String next : oneCharApart(word, dict, parentMap)) {
            parentMap.put(next, word);
            minLength = dfs(start, end, next,dict, result, parentMap, minLength);
            //backTrack
            parentMap.remove(next);
        }

        return minLength;
    }


    private static List<String> oneCharApart(String s, Set<String> dict, Map<String, String> parentMap) {
        List<String> result = new ArrayList<>();
        if(s == null || dict == null){
            return result;
        }
        for(String word : dict) {
            if(!parentMap.containsKey(word)) {
                if(oneCharApart(s,word)){
                    result.add(word);
                }
            }
        }

        return result;
    }

    private static boolean oneCharApart(String s1, String s2) {
        int diff = 0;
        if(s1.length() == s2.length()) {
            for( int i = 0; i< s1.length(); i++) {
                if(s1.charAt(i) != s2.charAt(i)) {
                    diff ++;
                }
            }
        }
        if(diff == 1) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        List<List<String>> result = wordLadder(start, end, dict);
        for(List<String> path : result){
            for(String word : path) {
                System.out.print(" " + word + " ");
            }
            System.out.println();
        }
    }
}
