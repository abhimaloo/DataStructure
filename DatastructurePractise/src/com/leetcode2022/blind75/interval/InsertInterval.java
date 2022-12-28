package com.leetcode2022.blind75.interval;

import java.util.ArrayList;
import java.util.List;

/*
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.



Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105
 */
public class InsertInterval {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> response = new ArrayList<>();
        boolean added = false;
        for (int i = 0; i < intervals.length; i++) {
            // if start time of intervals is bigger
            if (intervals[i][0] >= newInterval[0]) {
                // if newer interval finishes before existing interval than simple add it
                response.add(newInterval);
                added = true;
            }
            response.add(intervals[i]);
        }

        if (!added) {
            response.add(newInterval);
        }

        return mergeIntervals(response.toArray(new int[intervals.length + 1][]));
    }

    private static int[][] mergeIntervals(int[][] intervals) {
        List<int[]> merged = new ArrayList<>();
        int[] newInterval = null;
        int start = 0;
        int end = 1;
        for (int i = 0; i < intervals.length; i++) {
            if (newInterval == null) {
                newInterval = intervals[i];
                merged.add(newInterval);
                continue;
            }

            if (intervals[i][start] <= newInterval[end]) {
                // merge the intervals
                newInterval[end] = Math.max(newInterval[end], intervals[i][end]);
            } else {
                newInterval = intervals[i];
                merged.add(newInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
    }
}
