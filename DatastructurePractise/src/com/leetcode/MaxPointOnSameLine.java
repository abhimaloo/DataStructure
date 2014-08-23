package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line
 *
 * https://oj.leetcode.com/problems/max-points-on-a-line/
 * Created by abhimaloo on 8/22/14.
 */
public class MaxPointOnSameLine {
    public static int[][] points = {{1,1},
                                    {2,2},
                                    {3,3},
                                    {1,-1},
                                    {2,-2}};

    /**
     * Trick is to fix a point and try calculating slope(y2-y1/x2-x1 && x1!= x2) with every other point
     * keep a frequency count of slope and maxCount as well
     * finaly return the maxCount
     * @param points
     * @return
     */
    public static int maxPointOnSameLine(int[][] points) {
        Map<Double, Integer> slopeMap = new HashMap<>();
        int maxSlopeCount = Integer.MIN_VALUE;

        for( int i = 0; i< points.length; i++) {
            for( int j = i+1; j < points.length; j++) {
                // x2 cannot be equals to x1
                if(points[i][0] != points[j][0]) {
                    // calculate slope
                    double slope = (points[j][1] - points[i][1])/ (points[j][0] - points[i][0]) ;
                    // check if slope is present in hashMap. If yes .. check if maxSlope needs any updation
                    if(slopeMap.containsKey(slope)) {
                        int existCount = slopeMap.get(slope);
                        // max slope needs updation ??
                        if(maxSlopeCount < existCount + 1) {
                            maxSlopeCount = existCount + 1;
                        }

                        slopeMap.put(slope, existCount + 1);
                    } else {
                        slopeMap.put(slope, 1);
                    }
                }
            }
        }

        return maxSlopeCount;
    }

    public static void main(String[] args) {
        System.out.println(maxPointOnSameLine(points));
    }
}
