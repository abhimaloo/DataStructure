package com.leetcode2022.blind75.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.



Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        List<int[]> merged = new ArrayList<>();
        int start = 0;
        int end = 1;
        if (intervals.length == 0) {
            return merged.toArray(new int[0][]);
        }
        // sort the interval arrays by start time
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[] newInterval = intervals[0];
        merged.add(newInterval);

        for (int i = 1; i < intervals.length; i++) {
            if (newInterval[end] >= intervals[i][start]) {
                newInterval[end] = Math.max(intervals[i][end], newInterval[end]);
            } else {
                newInterval = intervals[i];
                merged.add(newInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

}
