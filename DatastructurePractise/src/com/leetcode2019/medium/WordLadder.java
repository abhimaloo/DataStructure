package com.leetcode2019.medium;

import java.util.*;

/*
https://leetcode.com/problems/word-ladder/

 */
public class WordLadder {
    /*
    Regular BFS solution for shortest path
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.length() == 0 || endWord.length() == 0 || wordList.size() == 0 || beginWord.equals(endWord)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        queue.offer(beginWord);
        levelQueue.offer(0);
        while (!queue.isEmpty()) {
            String item = queue.poll();
            int level = levelQueue.poll();
            if (item.equals(endWord)) {
                return level;
            }
            visited.add(item);

            for (String word : wordList) {
                if (!visited.contains(word) && validJump(item, word)) {
                    queue.offer(word);
                    levelQueue.offer(level + 1);
                }
            }
        }
        return 0;
    }

    public static boolean validJump(String source, String target) {
        if (source.equals(target)) return false;
        if (target.length() != source.length()) {
            return false;
        }
        int mismatch = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) != target.charAt(i)) {
                if (mismatch == 1) {
                    return false;
                } else {
                    mismatch++;
                }
            }
        }

        return mismatch <= 1;
    }

    public int wordLadder(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return 0;
        Set<String> dict = new HashSet<>();
        for (String word : wordList) dict.add(word);

        if (!dict.contains(endWord)) return 0;
        int length = 1;

        Set<String> visited = new HashSet<>();
        Set<String> front = new HashSet<>();
        Set<String> back = new HashSet<>();

        front.add(beginWord);
        back.add(endWord);

        while (!front.isEmpty() && !back.isEmpty()) {
            // this trick is to move front and back both one after another whenever we find match we will exit
            if (front.size() > back.size()) {
                Set<String> t = front;
                front = back;
                back = t;
            }

            // here is the main logic
            // iterate through all the words in front
            // find the words which are 1 letter apart from the front and part of wordlist
            Set<String> next = new HashSet<>();
            for (String word : front) {
                char[] wordArray = word.toCharArray();
                for (int index = 0; index < wordArray.length; index++) {
                    for (int i = 0; i < 26; i++) {
                        char oldChar = wordArray[index];

                        wordArray[index] = (char) ('a' + i);
                        if (wordArray[index] == oldChar) continue;

                        String target = new String(wordArray);
                        if (back.contains(target)) {
                            return length + 1;
                        }

                        if (!visited.contains(target) && dict.contains(target)) {
                            next.add(target);
                            visited.add(target);
                        }
                        wordArray[index] = oldChar;
                    }
                }
            }
            front = next;
            length++;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }
}
