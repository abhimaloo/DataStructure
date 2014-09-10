package com.interview.arrays;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Problem is to find a median from incoming stream of integers. You cannot visit older elements
 * ex - {2,3,4,7,1,9,11}
 *
 *
 * Created by abhishekm787 on 7/23/14.
 */
public class MedianOfAStream {

    // this heap keeps elements of the greater half .. extract min of this heap will give minimum of greater half
    public static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    //this heap will keep elements of lower half .. extract max will return the max of the lower half
    public static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10,new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return -1*(o1.compareTo(o2));
        }
    });

    /**
     * idea is to maintain two heaps - one min heap and other max heap .. so that we can keep track of middle elements
     * always keep minHeap size>= maxHeap size ..
     *  whenever imbalamnce could occur rebalance the heaps by taking the top element out and push it into other heap
     *  finally return the median
     * @param number
     * @return
     */
    public static int findMedian(int number, int lastMedian) {

        // if existing size is even
        if((minHeap.size()+maxHeap.size()) %2  == 0) {
            //number belongs to min Heap
            if(number > lastMedian){
                minHeap.add(number);
            } else {
                // if number belongs to max heap ..we will need to balance the heaps
                // we cannot keep maxHeap size greater than min heap size
                //hence remove the top element from max heap
                int oldMax = maxHeap.remove();
                //add it to min heap
                minHeap.add(oldMax);
                //now add the incoming number to max heap
                maxHeap.add(number);
            }
            //for odd size.. median will be at top of the min heap
            return  minHeap.peek();
        } else {
            if(number > minHeap.peek()){
                int oldMin = minHeap.remove();
                maxHeap.add(oldMin);
                minHeap.add(number);
            } else {
                maxHeap.add(number);
            }
            // for even size median will be the average of two tops
            return (minHeap.peek() + maxHeap.peek())/2 ;
        }

    }

    public static void main(String[] args) {
       int [] stream = {2,8,6,7,3,9,11};
        int median = 0;
        for (int i = 0; i < stream.length; i++) {
           median = findMedian(stream[i], median);
            System.out.println("Median IS :"+ median);

        }
    }
}
