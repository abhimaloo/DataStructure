package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 For example,
 Given [100, 4, 200, 1, 3, 2],
 The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

 Your algorithm should run in O(n) complexity.

 https://oj.leetcode.com/problems/longest-consecutive-sequence/
 * Created by abhimaloo on 8/26/14.
 */
public class LongestConsecutiveSequence {
    public static int[] input = {100,4,200,1,3,2};

    /**
     * trick is to use hash set and put all the numbers in a hash set
     * Now iterate through each number and keep incrementing count if number -1 is present in hash set
     * similarly also check if number +1 is present in hash set and increment count.
     * finally update the max count find so far
     *
     *
     * @param input
     * @return
     */
    public static int longestConsecutiveSequence(int[] input) {

        Set<Integer> deduper = new HashSet<>();
        // putting all the unique numbers in hashSet
        for(int num : input) {
            deduper.add(num);
        }

        int max = 1;

        // iterate through numbers
        for(int num : input) {

            int left = num - 1;

            int right = num + 1;

            int count = 1;
            // check if number -1 ... number -n is present in hashSet
            while(deduper.contains(left)) {
                // increment the counter
                count++;
                // remove the number so that it will not be counted again for any other number
                deduper.remove(left);
                // go even more more level left
                left --;
            }

            // check if number +1 is present
            while(deduper.contains(right)) {
                // increment the count
                count++;
                // remove it from the set so that it will not be counted twice
                deduper.remove(right);
                // go to one more level right
                right++;
            }
            // update the max with maximum of count found so far
            max = Math.max(max, count);

        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutiveSequence(input));
    }

}
