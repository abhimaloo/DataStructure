package com.leetcode2019.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/meeting-rooms-ii/
iven an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt((o) -> o[0]));
        PriorityQueue<Interval> minHeap = new PriorityQueue<>(Comparator.comparingInt((i) -> i.end));
        minHeap.offer(new Interval(intervals[0][0], intervals[0][1]));
        for (int i = 1; i < intervals.length; i++) {
            Interval curr = new Interval(intervals[i][0], intervals[i][1]);
            Interval earlestEnding = minHeap.poll();
            if (curr.start >= earlestEnding.end) {
                earlestEnding.end = curr.end;
            } else {
                minHeap.offer(curr);
            }
            minHeap.offer(earlestEnding);
        }

        return minHeap.size();
    }
}

class Interval {
    public int start;
    public int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
