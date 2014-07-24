package com.interview.arrays;

import java.util.PriorityQueue;

/**
 * Given an array of n elements, where each element is at most k away from its target position,
 * devise an algorithm that sorts in O(n log k) time
 *
 * For example, let us consider k is 2,
 * an element at index 7 in the sorted array,
 * can be at indexes 5, 6, 7, 8, 9 in the given array
 * Created by abhishekm787 on 7/23/14.
 */
public class SortNearlySortedArray {
    public static int [] input =  {2, 6, 3, 12, 56, 8};
    public static int k = 3;

    public static int[] sortPartiallySortedElements(int[] input, int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        int[] response = new int[input.length];
        int i = 0; int j =0;
        for( ; i<k; i++){
            minHeap.add(input[i]);
        }

        while(minHeap.size()!=0){
           response[j++]  = minHeap.remove();
           if(i<input.length){
               minHeap.add(input[i++]);
           }

        }

        return response;

    }

    public static void main(String[] args) {
        int [] response = sortPartiallySortedElements(input,k);
        for (int i = 0; i < response.length; i++) {
            System.out.println(response[i]);

        }
    }


}
