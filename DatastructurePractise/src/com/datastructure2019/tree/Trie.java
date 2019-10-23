package com.datastructure2019.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    public TrieNode root = new TrieNode();

    public void insert(TrieNode root, String word) {
        for (char c : word.toCharArray()) {
            TrieNode node = root.children.get(c);
            if (node == null) {
                TrieNode newNode = new TrieNode();
                root.children.put(c, newNode);
                root = newNode;
            } else {
                root = node;
            }
        }
        root.isWord = true;
    }

    public void printWords(TrieNode root, String word) {
        if (root.isWord) {
            System.out.println(word);
        }

        for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {
            printWords(entry.getValue(), word + entry.getKey());
        }
    }

    public void visitWords(TrieNode root, String prefix, List<String> words) {
        if (root.isWord) {
            words.add(prefix);
        }
        for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {
            visitWords(entry.getValue(), prefix + entry.getKey(), words);
        }
    }

    public List<String> printSuggestion(TrieNode root, String prefix) {
        List<String> words = new ArrayList<>();
        for (char c : prefix.toCharArray()) {
            TrieNode node = root.children.get(c);
            if (node == null) {
                return words;
            }
            root = node;
        }
        // find all the words after the prefix
        visitWords(root, prefix, words);
        return words;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert(trie.root, "Abhi");
        trie.insert(trie.root, "Abhilasha");
        trie.insert(trie.root, "Abhilekha");
        //trie.printWords(trie.root, "");
        System.out.println(trie.printSuggestion(trie.root, "Abhil"));
    }
}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord;
}