package com.leetcode2022.blind75.graph;

import java.util.*;

/*
There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary, where the strings in words are
sorted lexicographically
 by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.



Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
Example 2:

Input: words = ["z","x"]
Output: "zx"
Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.
 */
public class AlienDictionary {
    public String alienOrder(String[] words) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Set<Character>> graph = new HashMap<>();
        Set<Character> visited = new HashSet<>();
        Set<Character> visiting = new HashSet<>();
        // first build the graph
        if (!buildGraph(words, graph)) {
            return "";
        }
        // now do topological sort
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                if (!visited.contains(c)) {
                    if (!topologicalSortAndFindCycle(c, graph, stack, visiting, visited)) {
                        return "";
                    }
                }
            }
        }


        StringBuffer response = new StringBuffer("");
        while (!stack.isEmpty()) {
            response.append(stack.pop());
        }

        return response.toString();
    }

    private boolean topologicalSortAndFindCycle(Character c, Map<Character, Set<Character>> graph, Stack<Character> stack, Set<Character> visiting, Set<Character> visited) {
        if (visiting.contains(c)) {
            return false;
        }

        if (visited.contains(c)) {
            return true;
        }
        visiting.add(c);

        // now do DFS
        for (Character ch : graph.get(c)) {
            if (!topologicalSortAndFindCycle(ch, graph, stack, visiting, visited)) {
                return false;
            }
        }
        visiting.remove(c);
        visited.add(c);
        stack.push(c);

        return true;
    }

    private boolean buildGraph(String[] words, Map<Character, Set<Character>> graph) {
        for (int i = 0; i < words.length; i++) {
            // create empty hashsets
            for (int j = 0; j < words[i].length(); j++) {
                if (!graph.containsKey(words[i].charAt(j))) {
                    graph.put(words[i].charAt(j), new HashSet<>());
                }
            }
            // starting from second word add the letter as dependencies if earlier letter at similar position is not same
            if (i + 1 < words.length) {
                if (words[i].length() > words[i + 1].length() && words[i].startsWith(words[i + 1])) {
                    return false;
                }
                int len = Math.min(words[i].length(), words[i + 1].length());
                for (int j = 0; j < len; j++) {
                    if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                        graph.get(words[i].charAt(j)).add(words[i + 1].charAt(j));
                        break;
                    }
                }
            }
        }
        return true;
    }
}
