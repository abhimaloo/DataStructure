package com.leetcode2019.hard;

import java.util.Arrays;

/*
https://leetcode.com/problems/russian-doll-envelopes/
You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelops {
    /*
    Trick is  -  we can sort the envelops by Width Asc, Height Desc
    this way we can then just run the length longest increasing subsequnce and that would be our answer
     */
    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes.length == 0) return 0;
        if (envelopes.length == 1) return 1;
        // first sort the envelops by its width
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1]; // decreasing order of height
            } else {
                return o1[0] - o2[0]; // increasing order of width
            }
        });

        int height[] = new int[envelopes.length];

        for (int i = 0; i < envelopes.length; i++) {
            height[i] = envelopes[i][1];
        }

        // now find the longest increasing subsequence
        int dp[] = new int[height.length];
        Arrays.fill(dp, 1);
        int maxLength = 1;
        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < i; j++) {
                if (height[j] <= height[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[][] env = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        RussianDollEnvelops obj = new RussianDollEnvelops();
        System.out.println(obj.maxEnvelopes(env));
    }
}
