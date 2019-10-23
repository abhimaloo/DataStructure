package com.leetcode2019.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
https://leetcode.com/problems/merge-intervals/
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.


 */
public class MergeIntervals {
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        List<int[]> merged = new ArrayList<>();
        int start = 0;
        int end = 1;

        // first sort the arrays by start time
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        // Keep first interval in
        int[] newInterval = intervals[0];
        merged.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[start] <= newInterval[end]) {
                newInterval[end] = Math.max(interval[end], newInterval[end]);
            } else {
                newInterval = interval;
                merged.add(newInterval);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };
        int[][] merged = merge(intervals);
    }
}
