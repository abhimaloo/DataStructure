package com.maloo.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhishekm787 on 7/16/14.
 */
public class SuffixTrie {

    public SuffixNode root;

    public SuffixTrie(){
        root = new SuffixNode('\0', new SuffixNode[26], false);
    }

    public void insert(String s, Callback c) {
       int offset = 97;
       char[] letter = s.toCharArray();
       String suffix = "";
       // this will got through
       for(int j = letter.length-1;j>=0; j--){
           SuffixNode cursor = root;
           //very important.. new letter will pe prepended in front of old suffix
           suffix = letter[j]+suffix;
           char [] suffixs = suffix.toCharArray();
           // go through each suffix and insert it into trie
           for(int i=0;i<suffixs.length; i++){

               if(cursor.links[suffixs[i]-offset] == null){
                   cursor.links[suffixs[i]-offset] = new SuffixNode(suffixs[i], new SuffixNode[26], false);
                   cursor.linkSize++;
               }

               cursor = cursor.links[suffixs[i]-offset];

               // callback pattern implemented..
               if(c!=null){
                   c.callMe(cursor, suffix);
               }
           }
           cursor.fullWord = true;

       }

    }


    public void inOrder(SuffixNode root, Processable callback){

        //process it here
        callback.process(root);

        for(int i =0; i<root.links.length; i++){
            if(root.links[i]!=null){
                inOrder(root.links[i], callback);
            }
        }

    }


    public static String longestCommonSubsequence(String s1, String s2) {
        SuffixTrie trie = new SuffixTrie();
        final StringBuilder result = new StringBuilder();
        trie.insert(s1, new Callback() {
            @Override
            public void callMe(SuffixNode node, String suffix) {
                node.bag.put("String1","1");
            }

        });


        trie.insert(s2, new Callback() {
            @Override
            public void callMe(SuffixNode node, String suffix) {
                node.bag.put("String2","2");
            }

        });

        trie.inOrder(trie.root,new Processable() {
            @Override
            public void process(SuffixNode node) {
               if(node.bag.containsKey("String1") && node.bag.containsKey("String2")) {
                   result.append(node.letter);
               }
            }
        });

      return result.toString();
    }



    private interface Callback{
        public void callMe(SuffixNode node, String suffix);

    }

    private interface Processable{
        public void process(SuffixNode node);
    }

    public static class SuffixNode{
        char letter;
        SuffixNode[] links;
        int linkSize = 0;
        boolean fullWord;
        Map<String,String> bag = new HashMap();


        public SuffixNode(char letter, SuffixNode[] links, boolean fullWord) {
            this.letter = letter;
            this.links = links;
            this.fullWord = fullWord;
        }
    }

    public static void main(String[] args) {
        System.out.println(SuffixTrie.longestCommonSubsequence("ababc","babca"));


    }
}
