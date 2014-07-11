package com.maloo.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Very useful datastructure to save Strings and later search them by prefixes
 * Most useful in autocorrect or auto suggest type scenarios
 * Created by abhimaloo on 7/10/14.
 */
public class TrieTree {

    //root node contains NULL as character and 26 unintialized objects for every letter
    private TrieNode root  = new TrieNode('\0',new TrieNode[26], false);


    public  void insert(TrieNode root, String word) {
        //its an ASCII value of 'a'
        int offset = 97;
        char[] letters = word.toCharArray();

        TrieNode cursor = root;
        for(int i=0;i<letters.length;i++) {
            // letter[i] - offset will return 0 ..26 for a..z
            if(cursor.links[letters[i]-offset] == null){
                cursor.links[letters[i]-offset]  = new TrieNode(letters[i],new TrieNode[26], false);
            }

            cursor = cursor.links[letters[i]-offset];
        }

        cursor.fullWord = true;
    }

    public boolean find(TrieNode root, String word) {
        int offset = 97;
        char[] letters = word.toCharArray();
        TrieNode cursor = root;
        for(int i=0;i<letters.length;i++) {
            if(cursor.links[letters[i]-offset] == null){
                return false;
            }
            cursor = cursor.links[letters[i]-offset];
        }

        return cursor.fullWord;
    }

    /**
     * printing recursively the word
     * backtracking type approach
     *
     * @param root
     * @param path
     */
    public void print(TrieNode root, StringBuilder path) {

        path.append(root.letter);
        if(root.fullWord){
            System.out.println(path.toString());
        }

        //check all possible next steps and push them in
        for(int i =0; i <26; i++){
            if(root.links[i] !=null){
               print(root.links[i], path);
            }
        }

        // delete the last character while popping out
        path.deleteCharAt(path.length()-1);

    }


    public void printAllByPrefix(TrieNode root, String prefix) {

        int offset = 97;
        char[] letters = prefix.toCharArray();
        TrieNode cursor = root;
        for( int i=0; i< prefix.length(); i++){
            if(cursor.links[letters[i]-97] == null){
                return;
            } else {
               cursor = cursor.links[letters[i]-97];
            }
        }

        //cursor is at last character ..hence delete the last character from prefix and pass it to print method
        print(cursor, new StringBuilder(prefix.substring(0,prefix.length()-1)));

    }


    /**
     * Recursive approach
     * keep adding the words to the list of results
     * @param root
     * @param word
     * @param result
     */
    public void findWordAfterPrefix(TrieNode root, StringBuilder word, List<String> result) {

        word.append(root.letter);
        if(root.fullWord){
            //add the complete word to result list
            result.add(word.toString());
        }

        //check all possible next steps and push them in
        for(int i =0; i <26; i++){
            if(root.links[i] !=null){
                findWordAfterPrefix(root.links[i], word, result);
            }
        }

        // delete the last character while popping out
        word.deleteCharAt(word.length()-1);
    }

    public List<String> findAllByPrefix(TrieNode root, String prefix) {

        int offset = 97;
        char[] letters = prefix.toCharArray();
        TrieNode cursor = root;
        for( int i=0; i< prefix.length(); i++){
            if(cursor.links[letters[i]-97] == null){
                return null;
            } else {
                cursor = cursor.links[letters[i]-97];
            }
        }

        //cursor is at last character ..hence delete the last character from prefix and pass it to print method
        List<String> result = new ArrayList<>();
        findWordAfterPrefix(cursor, new StringBuilder(prefix.substring(0,prefix.length()-1)), result);
        return result;

    }


    /**
     * very usefull method.
     * It can provide List of words based on a prefix which could be correct or misspelled
     * @param root
     * @param prefix
     * @return
     */
    public List<String> findSuggestionByPrefix(TrieNode root, String prefix){

        List<String> result = new ArrayList<>();
        int offset = 97;
        char[] letters = prefix.toCharArray();
        TrieNode cursor = root;
        for( int i=0; i< prefix.length(); i++){
            //if character does not match .. try finding out all the possible words from the matching node
            if(cursor.links[letters[i]-97] == null){
                //remove two last letters from prefix since last one does not match and second last will be taken care
                //anyways in the called function
                findWordAfterPrefix(cursor, new StringBuilder(prefix.substring(0,prefix.length()-2)), result);
                return result;
            } else {
                cursor = cursor.links[letters[i]-97];
            }
        }

        //cursor is at last character ..hence delete the last character from prefix and pass it to print method
        findWordAfterPrefix(cursor, new StringBuilder(prefix.substring(0,prefix.length()-1)), result);
        return result;
    }


    /**
     * TrieNode contains
     * letter - which represents the letter at this node
     * links - childrens or pointers to next character
     * fullWord - specifies that this node ends a full word
     */
    public static class TrieNode{
        char letter;
        TrieNode[] links;
        boolean fullWord;

        public TrieNode(char letter, TrieNode[] links, boolean fullWord) {
            this.letter = letter;
            this.links = links;
            this.fullWord = fullWord;
        }
    }

    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.insert(tree.root, "abhishek");
        tree.insert(tree.root,"abhudi");
        tree.insert(tree.root,"abhudilal");
        //System.out.println(tree.find(tree.root,"abhishek"));
        //tree.print(tree.root, new StringBuilder());
        for(String word: tree.findSuggestionByPrefix(tree.root, "abh")) {
            System.out.println(word);
        }
    }
}
