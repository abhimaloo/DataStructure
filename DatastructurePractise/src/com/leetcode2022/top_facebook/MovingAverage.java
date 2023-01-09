package com.leetcode2022.top_facebook;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Implement the MovingAverage class:

MovingAverage(int size) Initializes the object with the size of the window size.
double next(int val) Returns the moving average of the last size values of the stream.


Example 1:

Input
["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
Output
[null, 1.0, 5.5, 4.66667, 6.0]

Explanation
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3


Constraints:

1 <= size <= 1000
-105 <= val <= 105
At most 104 calls will be made to next.
 */
public class MovingAverage {
    public int size = 0;
    public int totalSum = 0;
    public Queue<Integer> queue = null;

    public MovingAverage(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }

    public double next(int val) {
        if (queue.size() >= size) {
            totalSum -= queue.poll();
        }
        queue.offer(val);
        totalSum += val;
        return (double) totalSum / queue.size();
    }

    public static void main(String[] args) {
        MovingAverage avg = new MovingAverage(3);
        avg.next(1);
        avg.next(10);
        avg.next(3);
        System.out.println(avg.next(5));
    }
}
