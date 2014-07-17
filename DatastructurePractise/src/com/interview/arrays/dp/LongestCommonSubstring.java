package com.interview.arrays.dp;

import com.maloo.trees.TrieTree;

/**
 * find the longest common substring between two strings "abcde" and "xabcz".
 * result should be "abc"
 * Created by abhishekm787 on 7/16/14.
 */
public class LongestCommonSubstring {
    public static String findLongestCommonSubstringUsingTries(String a, String b) {
        TrieTree suffix = new TrieTree();
        suffix.insert(suffix.root, a);
        suffix.insert(suffix.root,b);


        return null;
    }

    public static void main(String[] args) {
        System.out.println(findLongestCommonSubstringUsingTries("abcde", "xabcz"));
    }
}
