package com.leetcode2019.hard;

import java.util.*;

/*
https://leetcode.com/problems/alien-dictionary/
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDictionary {
    public static String alienOrder(String[] words) {
        Stack<Character> result = new Stack<>();
        Map<Character, Set<Character>> graph = new HashMap<>();
        buildGraph(words, graph);
        Set<Character> visited = new HashSet<>();
        Set<Character> visiting = new HashSet<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (!visited.contains(word.charAt(i))) {
                    if (!sort(graph, word.charAt(i), visited, visiting, result)) {
                        return "";
                    }
                }
            }
        }
        // do topological sort
        StringBuilder res = new StringBuilder();
        while (!result.isEmpty()) res.append(result.pop());
        return res.toString();
    }

    public static boolean sort(Map<Character, Set<Character>> graph, char c, Set<Character> visited, Set<Character> visiting, Stack<Character> result) {
        if (visiting.contains(c)) return false;

        if (visited.contains(c)) return true;

        visiting.add(c);
        for (char t : graph.get(c)) {
            if (!sort(graph, t, visited, visiting, result)) {
                return false;
            }
        }
        visiting.remove(c);
        visited.add(c);
        result.push(c);
        return true;
    }

    public static void buildGraph(String[] words, Map<Character, Set<Character>> graph) {
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (!graph.containsKey(words[i].charAt(j))) {
                    graph.put(words[i].charAt(j), new HashSet<>());
                }
            }
            // starting from second word
            if (i + 1 < words.length) {
                int len = Math.min(words[i].length(), words[i + 1].length());
                for (int j = 0; j < len; j++) {
                    if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                        graph.get(words[i].charAt(j)).add(words[i + 1].charAt(j));
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"za", "zb", "ca", "cb"};
        System.out.println(alienOrder(words));
    }
}
