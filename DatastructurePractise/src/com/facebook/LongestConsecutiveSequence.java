package com.facebook;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 For example,
 Given [100, 4, 200, 1, 3, 2],
 The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

 Your algorithm should run in O(n) complexity.

 * Created by abhimaloo on 9/27/14.
 */
public class LongestConsecutiveSequence {

    public static int[] input = {100, 4, 200, 1, 3, 2};

    public static int longestConseqSequence(int[] input) {

        Set<Integer> dedup = new HashSet<>();
        int max = 0;
        if(input == null|| input.length == 0) {
            return max;
        }

        for(int i: input) {
            dedup.add(i);
        }

        for(int i: input) {
            int left = i-1;
            int right =i+1;
            int count = 1;

            while(dedup.contains(left)) {
                count ++;
                dedup.remove(left--);
            }

            while(dedup.contains(right)) {
                count ++;
                dedup.remove(right++);
            }


            max = Math.max(max, count);
        }

        return max;
    }

    public static void main(String[] args) {

        System.out.println(longestConseqSequence(input));
    }

}
