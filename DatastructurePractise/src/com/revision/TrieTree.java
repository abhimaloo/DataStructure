package com.revision;



/**
 * Created by abhimaloo on 9/21/14.
 */
public class TrieTree {
    class TrieNode {
        public char data;
        public  boolean isWord ;
        public TrieNode[] links = new TrieNode[26];
    }

    public TrieNode root = null;

    public TrieTree() {
        root = new TrieNode();
        root.data = '\0';
    }

    public void insert(TrieNode root, String word) {


        for(int i = 0; i< word.length(); i++) {
            if(root.links[word.charAt(i)-'A'] == null) {
                TrieNode newNode = new TrieNode();
                newNode.data = word.charAt(i);
                root.links[word.charAt(i)-'A'] = newNode;
            }
            root = root.links[word.charAt(i)-'A'];
        }

        root.isWord = true;

    }


    public void printAllWords(TrieNode root, String word) {

        word += root.data;
        if(root.isWord) {
            System.out.println(word);
        }

        for( int i = 0; i< 26; i++) {
           if(root.links[i] != null) {
               printAllWords(root.links[i], word);
           }

        }

    }

    public static void main(String[] args) {
        TrieTree trie = new TrieTree();
        trie.insert(trie.root,"ABHISHEK");
        trie.insert(trie.root,"ABHILEKHA");
        trie.insert(trie.root,"ANJALI");
        trie.printAllWords(trie.root, "");

    }






}
