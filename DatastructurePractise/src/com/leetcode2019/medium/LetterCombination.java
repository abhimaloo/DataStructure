package com.leetcode2019.medium;

import java.util.*;

/*
https://leetcode.com/problems/letter-combinations-of-a-phone-number/
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombination {
    Map<Integer, List<Character>> map = new HashMap<>();

    public LetterCombination() {
        this.map.put(2, Arrays.asList('a', 'b', 'c'));
        this.map.put(3, Arrays.asList('d', 'e', 'f'));
        this.map.put(4, Arrays.asList('g', 'h', 'i'));
        this.map.put(5, Arrays.asList('j', 'k', 'l'));
        this.map.put(6, Arrays.asList('m', 'n', 'o'));
        this.map.put(7, Arrays.asList('p', 'q', 'r', 's'));
        this.map.put(8, Arrays.asList('t', 'u', 'v'));
        this.map.put(9, Arrays.asList('w', 'x', 'y', 'z'));
    }

    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if (digits == null || digits.length() == 0) return results;
        letterCombination(0, digits, "", results);
        return results;
    }

    public void letterCombination(int idx, String digits, String soFar, List<String> results) {
        if (idx >= digits.length()) {
            results.add(soFar);
            return;
        }

        for (Character c : this.map.get(Integer.parseInt(digits.substring(idx, idx + 1)))) {
            letterCombination(idx + 1, digits, soFar + c, results);
        }
    }

    public static void main(String[] args) {
        LetterCombination obj = new LetterCombination();
        obj.letterCombinations("23");
    }
}
