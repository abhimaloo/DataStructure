package com.facebook;

import java.util.*;

/**
 * Created by abhimaloo on 9/27/14.
 */
public class WordLadder {

    public static String start = "hit";
    public static String end = "cog";
    public static Set<String> dict = new HashSet<>(Arrays.asList("hot","dot","dog","lot","log"));

    public static int wordLadder(String start, String end, Set<String> dict) {
        if(start == null || end  == null) {
            return -1;
        }

        if(dict == null) {
            return start.equals(end) ? 0 : -1;
        }

        LinkedList<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        dict.add(end);
        queue.add(start);
        parentMap.put(start, null);

        while(!queue.isEmpty()) {
            String s = queue.removeFirst();
            if(s.equals(end)) {
               String parent = parentMap.get(s);
               int count = 1;
                System.out.println(parent);
               while(parent != null && !parent.equals(start)){
                   count ++;
                   parent = parentMap.get(parent);
                   System.out.println(parent);
               }
               return count;
            }

            for(String word : oneCharApart(s, dict, parentMap)) {
                parentMap.put(word, s);
                queue.add(word);
            }

        }

        return -1;
    }

    private static List<String> oneCharApart(String s, Set<String> dict, Map<String, String> parentMap) {
        List<String> result = new ArrayList<>();
        if(s == null || dict == null){
            return result;
        }

        for(String word : dict) {
            if(!parentMap.containsKey(word)) {
                if(word.length() == s.length()) {
                    int diff = 0;
                    for( int i = 0; i< s.length(); i++) {
                        if(s.charAt(i) != word.charAt(i)) {
                            diff++;
                        }
                    }
                    if(diff == 1) {
                        result.add(word);
                    }
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(wordLadder(start, end, dict));
    }
}
