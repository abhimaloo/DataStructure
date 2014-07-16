package com.interview.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by abhishekm787 on 7/15/14.
 * let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8} }.
 * The intervals {1,3} and {2,4} overlap with each other,
 * so they should be merged and become {1, 4}.
 * Similarly {5, 7} and {6, 8} should be merged and become {5, 8}
 */
public class MergeIntervals {
    public static int[][] intervals = {{1,3}, {2,4}, {5,7}, {6,8} };

    private static class Interval{
        public Integer startTime;
        public Integer endTime;

        public Interval(Integer startTime, Integer endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    /**
     * idea is to first sort the intervals by start time
     * now iterate through the intervals and see if two interval conflicts,
     * if they conflict merge them together and continue.
     * make sure you are considering the merged intervals again while moving forward
     * @param intervals
     * @return
     */
    public static List<Interval> mergeInterval(int[][] intervals){
        //first sort the intervals according to their start time
        List<Interval> intervalList = new ArrayList<>(intervals.length);
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            intervalList.add(new Interval(interval[0], interval[1]));
        }

        Collections.sort(intervalList, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
               return o1.startTime.compareTo(o2.startTime);
            }
        });

        for(int i=1; i<intervalList.size(); i++){
            //if start time i is less than end time of i-1 .. then there is a conflict ..
            //merge the two intervals
            if(intervalList.get(i).startTime < intervalList.get(i-1).endTime){
               //merge the intervals i and i-1 to i
               intervalList.get(i).startTime =  intervalList.get(i-1).startTime;
               intervalList.get(i).endTime = Math.max(intervalList.get(i).endTime, intervalList.get(i-1).endTime) ;

               //remove the earlier interval
               intervalList.remove(i-1);
               //decrease the count of i
               i--;

            }
        }

      return intervalList;

    }

    public static void main(String[] args) {
        for(Interval i: mergeInterval(intervals)){
            System.out.println(i.startTime+":"+ i.endTime);
        }

    }
}
