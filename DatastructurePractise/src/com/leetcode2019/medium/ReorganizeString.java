package com.leetcode2019.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/reorganize-string/
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString {
    public static String reorganizeString(String S) {
        int[] freqCount = new int[26];
        int maxCount = 0;
        for (int i = 0; i < S.length(); i++) {
            freqCount[S.charAt(i) - 'a']++;
            maxCount = Math.max(maxCount, freqCount[S.charAt(i) - 'a']);
            if (maxCount > (S.length() + 1) / 2) {
                return "";
            }
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.<int[]>comparingInt(a -> a[0]).reversed());
        for (int i = 0; i < 26; i++) {
            if (freqCount[i] != 0) {
                queue.offer(new int[]{freqCount[i], i});
            }
        }
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int[] first = queue.poll();
            char val = (char) (first[1] + 'a');
            if (sb.length() == 0 || val != sb.charAt(sb.length() - 1)) {  // if result has been initialized and last character does not match the polled element
                sb.append(val);
                if (--first[0] > 0) {  // if freq is still above 0 add it back to queue
                    queue.offer(first);
                }
            } else {
                if (!queue.isEmpty()) {
                    int[] second = queue.poll();  // else pull the next character out
                    char secondVal = (char) (second[1] + 'a');
                    sb.append(secondVal);   //append it at the end
                    if (--second[0] > 0) {   // if freq is till left add it back ot queue
                        queue.offer(second);
                    }
                }
                queue.offer(first);  // since  we did not use first; put first back into the queue
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reorganizeString("aaab"));
    }
}
