package com.leetcode2019.hard;

import java.util.ArrayList;
import java.util.List;

public class InsertIntervals {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> sortedIntervals = new ArrayList();
        if (newInterval.length == 0) return intervals;
        if (intervals.length == 0) {
            int[][] ret = new int[1][2];
            ret[0] = newInterval;
            return new int[][]{newInterval};
        }

        int i = 0;
        boolean added = false;
        for (int[] interval : intervals) {
            if (newInterval[0] <= interval[0]) {
                added = true;
                sortedIntervals.add(newInterval);
            }
            sortedIntervals.add(interval);
        }
        if (!added) sortedIntervals.add(newInterval);
        return merge(sortedIntervals.toArray(new int[intervals.length + 1][]));
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        List<int[]> res = new ArrayList();
        int start = 0;
        int end = 1;
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[start] <= newInterval[end]) {
                newInterval[end] = Math.max(newInterval[end], interval[end]);
            } else {
                newInterval = interval;
                res.add(newInterval);
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        //[[1,3],[6,9]][2,5]
        int[][] intervals = new int[][]{
                {1, 5},
        };

        int[][] merged = insert(intervals, new int[]{2, 3});
    }
}
