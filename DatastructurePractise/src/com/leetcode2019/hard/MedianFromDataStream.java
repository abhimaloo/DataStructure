package com.leetcode2019.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/find-median-from-data-stream/
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.


Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2


Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
public class MedianFromDataStream {
    public PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    public PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    public double median = 0;

    public void addNum(int num) {
        switch (Integer.compare(maxHeap.size(), minHeap.size())) {
            case 0:
                if (num < median) {
                    maxHeap.offer(num);
                    median = maxHeap.peek();
                } else {
                    minHeap.offer(num);
                    median = minHeap.peek();
                }
                break;

            case -1:
                if (num < median) {
                    maxHeap.offer(num);
                } else {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(num);
                }
                median = (double) (minHeap.peek() + maxHeap.peek()) / 2;
                break;
            case 1:
                if (num < median) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(num);
                } else {
                    minHeap.offer(num);
                }
                median = (double) (minHeap.peek() + maxHeap.peek()) / 2;
                break;
        }
    }

    public double findMedian() {
        return median;
    }

    public static void main(String[] args) {
        MedianFromDataStream med = new MedianFromDataStream();
        med.addNum(1);
        med.addNum(2);
        System.out.println(med.findMedian());
        //med.addNum(8);
        //med.addNum(10);
        //med.addNum(5);
        //System.out.println(med.findMedian());
    }
}
