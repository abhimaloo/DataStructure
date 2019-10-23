package com.leetcode2019.easy;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/moving-average-from-data-stream/
 */
public class MovingAverage {
    public Queue<Integer> store;
    public int maxSize = 0;
    public double totalSum = 0;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        maxSize = size;
        store = new LinkedList();
    }

    public double next(int val) {
        double avg = 0;
        if (store.size() < maxSize) {
            store.offer(val);
            totalSum += val;
            avg = (totalSum) / store.size();
        } else {
            int earliest = store.poll();
            store.offer(val);
            totalSum += val - earliest;
            avg = (totalSum) / store.size();
        }

        return avg;
    }

    public static void main(String[] args) {
        MovingAverage obj = new MovingAverage(3);
        System.out.println(obj.next(1));
        System.out.println(obj.next(10));
        System.out.println(obj.next(3));
        System.out.println(obj.next(5));
    }
}
