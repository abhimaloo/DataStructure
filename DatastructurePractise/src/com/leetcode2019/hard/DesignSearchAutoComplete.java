package com.leetcode2019.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DesignSearchAutoComplete {
    private SearchTrieNode root = new SearchTrieNode();
    private String buffer = "";

    public DesignSearchAutoComplete(String[] sentences, int[] times) {

        for (int i = 0; i < sentences.length; i++) {
            insertIntoTrie(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> results = new ArrayList<>();

        if (c == '#') {
            insertIntoTrie(buffer, 1);
            buffer = "";
            return results;
        }

        buffer += c;
        results = findHotSentences(buffer);
        return results;
    }

    private void insertIntoTrie(String sentence, int frequence) {
        SearchTrieNode cursor = root;
        int idx = 0;
        while (cursor != null && idx < sentence.length()) {
            if (cursor.children.containsKey(sentence.charAt(idx))) {
                cursor = cursor.children.get(sentence.charAt(idx));
                if (idx == sentence.length() - 1) {
                    if (cursor.isSentence) {
                        cursor.frquency += frequence;
                    } else {
                        cursor.isSentence = true;
                        cursor.frquency = frequence;
                    }
                }
            } else {
                SearchTrieNode newNode = new SearchTrieNode();
                if (idx == sentence.length() - 1) {
                    newNode.frquency = frequence;
                    newNode.isSentence = true;
                }
                cursor.children.put(sentence.charAt(idx), newNode);
                cursor = newNode;
            }
            idx++;
        }
    }

    private List<String> findHotSentences(String prefix) {
        Map<String, Integer> results = new HashMap<>();
        SearchTrieNode cursor = root;
        int idx = 0;
        while (idx < prefix.length()) {
            char a = prefix.charAt(idx++);
            if (cursor.children.containsKey(a)) {
                cursor = cursor.children.get(a);
            } else {
                return new ArrayList<>();
            }
        }
        dfs(cursor, prefix, results);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(results.entrySet());
        list.sort((o1, o2) -> {
            if (o1.getValue() == o2.getValue()) {
                return o1.getKey().compareTo(o2.getKey());
            } else if (o1.getValue() < o2.getValue()) {
                return 1;
            } else {
                return -1;
            }
        });
        return list.stream().map((e) -> e.getKey()).limit(3).collect(Collectors.toList());
    }

    private void dfs(SearchTrieNode cursor, String currString, Map<String, Integer> results) {
        if (cursor.isSentence) {
            results.put(currString, cursor.frquency);
        }

        for (Character c : cursor.children.keySet()) {
            dfs(cursor.children.get(c), currString + c, results);
        }
    }

    public static void main(String[] args) {
        DesignSearchAutoComplete ac = new DesignSearchAutoComplete(
                new String[]{"abc", "abbc", "a"}, new int[]{3, 3, 3}
        );
        ac.input('b');
        ac.input('c');
        ac.input('#');
        ac.input('b');
        ac.input('c');
        ac.input('#');
        ac.input('a');
        ac.input('b');
        ac.input('c');
        ac.input('#');
        List<String> results = ac.input('a');


    }
}

class SearchTrieNode {
    Map<Character, SearchTrieNode> children = new HashMap<>();
    boolean isSentence;
    int frquency;
}