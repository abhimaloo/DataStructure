package com.interview.trees.impl;

/**
 * Created by abhimaloo on 8/10/14.
 */
public class TrieNode {
    public String data;
    public TrieNode[] links;
    public boolean fullWord;

    public TrieNode(String data, TrieNode[] links, boolean fullWord) {
        this.data = data;
        this.links = links;
        this.fullWord = fullWord;
    }
}
