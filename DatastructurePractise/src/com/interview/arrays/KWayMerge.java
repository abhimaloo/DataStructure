package com.interview.arrays;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by abhimaloo on 7/17/14.
 */
public class KWayMerge {
    public static int[][] input = {{1,3,5,7},
                                   {2,4,6,8,10},
                                   {4,9,11,13}};


    /**
     * trick is to use a minHeap for keeping the lowest k numbers
     *
     * @param input
     * @return
     */
    public static int[] kWayMerge(int[][] input){
        // this is just preparation step
        // trying to calculate total number of elements so that we can save it in result
        int totalLength = 0;
        for (int i = 0; i < input.length; i++) {
            totalLength += input[i].length;
        }

        //this array is to keep the index location of all the subarrays
        int [] index = new int[input.length];

        // this is the container for final results
        int[] result = new int[totalLength];
        int resultIndex = 0;

        // Use a heap of Pair to keep lowest k numbers
        PriorityQueue<Pair> heap = new PriorityQueue<>(input.length, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.value.compareTo(o2.value);
            }
        });

       // add all the smallest elements into the heap from all the subarrays
        for(int k = 0; k< input.length; k++){
            heap.add(new Pair(k,input[k][0]));
            index[k]++;
        }

        // loop till heap is not empty
        while(!heap.isEmpty()){
            //remove the min
            Pair min = heap.remove();
            // update the result
            result[resultIndex++] = min.value;
            // find the next donor.. next donor should be from the same array whose element is minimum
            if(index[min.rowNum] < input[min.rowNum].length){
                //min.rowNum identifies the subArray and index[min.rowNum] identifies the index on that subarray
                heap.add(new Pair(min.rowNum,input[min.rowNum][index[min.rowNum]++]));
            }
        }


        return result;
    }

    /**
     * This class is a container for keeping the rowId of the minimum value
     * It helps in identifying the subarray from which next element should be removed
     */
    public static class Pair{
        // rowNum denotes the subArray id
        public Integer rowNum;
        // valu is the current value
        public Integer value;

        public Pair(Integer rowNum, Integer value) {
            this.rowNum= rowNum;
            this.value = value;
        }
    }

    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);

        }

    }

    public static void main(String[] args) {
       //output should be sorted
        printArray(kWayMerge(input));
    }

}
