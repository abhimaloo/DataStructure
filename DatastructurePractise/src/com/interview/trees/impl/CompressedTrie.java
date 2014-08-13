package com.interview.trees.impl;

import java.util.Arrays;

/**
 * Very tricky to implement but saves a lot of time
 * Created by abhimaloo on 8/12/14.
 */
public class CompressedTrie {
    public TrieNode root = new TrieNode("\0", new TrieNode[26], false);


    public void insert(TrieNode root, String word) {

        for(int i=0; i< word.length(); i++) {
            // if entry character does not match create a new node
            if(root.links[word.charAt(i) - 97] == null) {
                // create a new trie node
                root.links[word.charAt(i) - 97] = new TrieNode(word.substring(i, word.length()), new TrieNode[26], true);
                // advance the root to this
                root = root.links[word.charAt(i) - 97];
                break;
            } else {
                //if there is a link present
                String data = root.links[word.charAt(i) - 97].data;
                int j=0;
                // try matching the data on that link with the word till it matches
                for(; j< data.length(); j++) {
                    if(word.charAt(i+j) != data.charAt(j)){
                        break;
                    }
                }
                //capture the data which matched
                String newData = data.substring(0, j);
                 // capture the data which is remaining
                String remainingData = data.substring(j, data.length());
                // if remaining has some characters
                if(remainingData.length() > 0){
                    // copy over existing links since we are going to split the node into two
                    TrieNode [] existingLinks = Arrays.copyOf(root.links[word.charAt(i) - 97].links, root.links[word.charAt(i) - 97].links.length);
                    // clean the links node
                    root.links[word.charAt(i) - 97].links = new TrieNode[26];
                    // create a node with remaining data which is child of the node we are splitting
                    // copy the existing links and full word from the parent
                    root.links[word.charAt(i) - 97].links[remainingData.charAt(0) - 97] = new TrieNode(remainingData, existingLinks, root.links[word.charAt(i) - 97].fullWord);
                    // make the parent node contain data which matched
                    root.links[word.charAt(i) - 97].data = newData;
                    // since its an intermediate node turn off fullWord for it
                    root.links[word.charAt(i) - 97].fullWord = false;
                }
                // proceed to the next root
                root = root.links[word.charAt(i) - 97];
                // advance the i by characters we have matched ..
                i = i+j-1;
            }

        }

    }



    public static void main(String[] args) {

        CompressedTrie tree  = new CompressedTrie();
        tree.insert(tree.root, "abhishek");
        tree.insert(tree.root, "abhijeet");
        tree.insert(tree.root, "anish");
        tree.insert(tree.root, "abhishekji");
    }

}
