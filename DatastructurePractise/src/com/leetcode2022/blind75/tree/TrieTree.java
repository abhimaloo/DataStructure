package com.leetcode2022.blind75.tree;

import java.util.HashMap;
import java.util.Map;

public class TrieTree {
    public TrieNode root = null;

    /**
     * Initialize your data structure here.
     */
    public TrieTree() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode cursor = root;
        for (char c : word.toCharArray()) {
            if (!cursor.children.containsKey(c)) {
                cursor.children.put(c, new TrieNode());
            }
            cursor = cursor.children.get(c);
        }

        cursor.isWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode cursor = root;
        for (char c : word.toCharArray()) {
            cursor = cursor.children.get(c);
            if (cursor == null) {
                return false;
            }
        }

        return cursor.isWord;
    }

    public boolean searchWithWildCard(String pattern, TrieNode cursor) {
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!cursor.children.containsKey(c)) {
                if (c == '.') { // for wildcard search in all the subNodes
                    for (char key : cursor.children.keySet()) {
                        if (searchWithWildCard(pattern.substring(i + 1), cursor)) {
                            return true;
                        }
                    }
                }
                return false;
            } else {
                cursor = cursor.children.get(c);
            }
        }

        return cursor.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode cursor = root;
        for (char c : prefix.toCharArray()) {
            cursor = cursor.children.get(c);
            if (cursor == null) {
                return false;
            }
        }

        return true;
    }

}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord;
}