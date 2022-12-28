package com.leetcode2022.blind75.interval;

import java.util.Arrays;
import java.util.Comparator;

/*
Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.



Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104
 */
public class NonOverLappingIntervals {
    /*
    Idea is to count all the non overlapping intervals and substract it from the total intervals
    We can sort all the intervals by the endTime and
    check if anyEndTime < start time of some other interval whose end time is higher or same
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int count = 1;
        if (intervals.length == 0) return 0;
        // sort the interval arrays by the end time
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int endTime = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= endTime) {
                endTime = intervals[i][1];
                count++;
            }
        }

        return intervals.length - count;
    }

}
