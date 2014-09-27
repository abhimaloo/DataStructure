package com.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Created by abhimaloo on 9/26/14.
 */
public class MaxPointsOnALine {

    public static int[][] points = {{1,1},
            {2,2},
            {3,3},
            {1,-1},
            {2,-2}};

    /**
     * trick is to calculate the slope from every point to every other point ..
     * maintain the frequency count for slope. update the maxPoints on which adding to frequency count
     * @param points
     * @return
     */
    public static int findMaxPointsOnSameLine(int[][] points) {

        int maxPoints = 0;
        if(points == null) {
            return maxPoints;
        }


        Map<Double, Integer> slopMap = new HashMap<>();

        for( int i = 0; i< points.length; i++) {
            for( int j = i+1; j<points.length; j++) {

                if(points[j][0] - points[i][0] != 0) {
                    double slope = ((double)points[j][1] - (double)points[i][1])/((double)(double)points[j][0] - (double)points[i][0]) ;
                    if(!slopMap.containsKey(slope)) {
                        slopMap.put(slope, 0);
                    }
                    slopMap.put(slope, slopMap.get(slope) +1);

                    if(slopMap.get(slope) > maxPoints) {
                        maxPoints = slopMap.get(slope);
                    }
                }

            }
        }

        return maxPoints;
    }

    public static void main(String[] args) {
        System.out.println(findMaxPointsOnSameLine(points));
    }



}
