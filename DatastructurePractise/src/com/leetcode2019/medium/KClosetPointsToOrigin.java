package com.leetcode2019.medium;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/k-closest-points-to-origin/
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 */
public class KClosetPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(K, (o1, o2) -> {
            if (o1.distance < o2.distance) return 1;
            else if (o1.distance > o2.distance) return -1;
            else return 0;
        });
        for (int i = 0; i < points.length; i++) {
            double distance = Math.sqrt((points[i][0] * points[i][0]) + (points[i][1] * points[i][1]));
            Point p = new Point(i, distance);
            if (maxHeap.size() < K) {
                maxHeap.offer(p);
            } else if (maxHeap.peek().distance > distance) {
                maxHeap.poll();
                maxHeap.offer(p);
            }
        }

        int[][] res = new int[K][2];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            res[i++] = points[maxHeap.poll().x];
        }

        return res;
    }
}

class Point {
    int x;
    double distance;

    public Point(int x, double distance) {
        this.x = x;
        this.distance = distance;
    }
}
