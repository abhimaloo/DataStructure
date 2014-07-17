package com.maloo.trees;

/**
 * Created by abhishekm787 on 7/16/14.
 */
public class SuffixTrie {
    public TrieTree tree = null;

    public SuffixTrie(){
        this.tree = new TrieTree();
    }

    public void insert(String s) {
        String suffix = "";
        for(int i = (s.length()-1); i>=0; i--){
            suffix = s.charAt(i)+suffix;
            this.tree.insert(tree.root, suffix);
        }
    }



    public static void main(String[] args) {
        SuffixTrie suffixTree = new SuffixTrie();
        suffixTree.insert("banana");
    }
}
