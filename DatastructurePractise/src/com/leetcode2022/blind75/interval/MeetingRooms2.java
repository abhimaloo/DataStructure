package com.leetcode2022.blind75.interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.



Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1


Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106
 */
public class MeetingRooms2 {
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        // sort them by start time
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        // keep a min heap of earliest end times
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        minHeap.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] currInterval = minHeap.poll();
            if (intervals[i][0] >= currInterval[1]) {  // this means the same room can host the newer interval as well
                currInterval[1] = intervals[i][1];  // increase the end time of the room
            } else {
                minHeap.offer(intervals[i]); // add new room
            }
            minHeap.offer(currInterval); // update the current Interval if the end time got changed
        }

        return minHeap.size();
    }

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][]{{2, 15}, {36, 45}, {9, 29}, {16, 23}, {4, 9}}));
    }
}
