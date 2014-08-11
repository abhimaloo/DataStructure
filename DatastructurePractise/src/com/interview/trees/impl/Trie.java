package com.interview.trees.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhimaloo on 8/10/14.
 */
public class Trie {
    public TrieNode root = new TrieNode("\0", new TrieNode[26], false);


    /**
     * Idea is to go through each letter of the word and find if there is a correposding node in trie already ..
     * if not create a new one
     * @param root
     * @param word
     */
    public void insert(TrieNode root, String word) {


        //iterate through word
        for( int i=0; i<word.length(); i++) {
            // if no node is present
            if(root.links[word.charAt(i) - 97] == null) {
                // create a new node
                root.links[word.charAt(i) - 97] = new TrieNode(String.valueOf(word.charAt(i)),new TrieNode[26], false);
            }
            // increment the cursor
            root = root.links[word.charAt(i) - 97];
        }
        // mark the full word to true
        root.fullWord = true;
    }

    // this method is used to print all the words in the trie
    public void printDFS(TrieNode root, String word) {
        // keep on appending characters
        word += root.data;
        // if it is full word .. print it
        if(root.fullWord) {
            System.out.println(word);
        }

       for( int i = 0; i< 26; i++) {
           // recurse of every not null link
           if(root.links[i] != null) {
               printDFS(root.links[i], word);
           }
       }

    }

    public List<String>  printSuggestions(TrieNode root, String prefix) {
        List<String> results = new ArrayList<>();
        for( int i = 0; i< prefix.length(); i++) {
            if(root.links[prefix.charAt(i) - 97] == null) {
                return results;
            }
            root = root.links[prefix.charAt(i) - 97];
        }

        prefixDFS(root, prefix.substring(0, prefix.length() -1), "", results);

        return results;
    }

    public void prefixDFS(TrieNode root,String prefix, String word, List<String> results) {
        word += root.data;
        if(root.fullWord) {
            results.add(prefix + word);
        }

        for( int i=0; i< 26; i++) {
            if(root.links[i] != null) {
                prefixDFS(root.links[i], prefix, word, results);
            }
        }

    }



    public static void main(String[] args) {
        Trie tree = new Trie();
        tree.insert(tree.root, "abhishek");
        tree.insert(tree.root, "abhi");
        tree.insert(tree.root, "anjali");

        for(String result : tree.printSuggestions(tree.root, "abh")) {
            System.out.println(result);
        }
    }

}
