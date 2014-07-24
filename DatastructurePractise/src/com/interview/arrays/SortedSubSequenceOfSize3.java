package com.interview.arrays;

/**
 * Given an array of n integers, find the 3 elements such that
 * a[i] < a[j] < a[k] and i < j < k in 0(n) time.
 * If there are multiple such triplets, then print any one of them.
 *
 * Ex  - Input:  arr[] = {12, 11, 10, 5, 6, 2, 30}
 Output: 5, 6, 30
 * Created by abhimaloo on 7/24/14.
 */
public class SortedSubSequenceOfSize3 {
    public static int[] input = {12, 11, 10, 5, 6, 2, 30};

    /**
     * trick is to make a pass in the array and fill two arrays smalleer and bigger such that
     * smaller contains the index of any one element smaller to input[i] and residing on left of it.
     * While bigger contains the index of any one element bigger that input[i] residing on right of it.
     *
     * finally scan both the arrays to find an index which has non negative index
     * smaller[i], i and bigger[i] will contain the resultant indices.
     * @param input
     * @return
     */
    public static int[] findSortedSubsequence(int[] input){
        //smaller will hold elements smaller and on the left of a given array index i
        int[] smaller = new int[input.length];
        //bigger will hold elements greater and on the right of a given array index i
        int[] bigger = new int[input.length];
        //base cases

        int[] response = new int[3];

        smaller[0] = -1;
        bigger[input.length-1] = -1;
        int min =0;
        int max = input.length-1;

        for (int i = 1; i < input.length; i++) {

            //fill smaller subarray
            //try maintaining the minimum found so far and keep comparing the element with minimum so far
            if(input[i] <= input[min]){
                min = i;
                smaller[i] = -1;
            } else {
                smaller[i] = min;
            }

            //fill bigger subarray
            //try maintaining the max found so far
            int j  = input.length-i -1;
            if(input[j]<=input[max]){
                bigger[j] = max;
            } else {
                max = j;
                bigger[j] = -1;
            }

        }

        //now construct the response by iterating over smaller and bigger array alements
        //try to find an elemtent which has non-negative integer in both smaller and bigger array
        for(int i = 0; i< input.length; i++) {
            if(smaller[i] != -1 && bigger[i] != -1){
                response[0] = input[smaller[i]];
                response[1] = input[i];
                response[2] = input[bigger[i]];
                break;
            }
        }
        return response;

    }

    public static void main(String[] args) {
        int[] response  = findSortedSubsequence(input);
        for (int i = 0; i < response.length; i++) {
            System.out.println(response[i]);

        }
    }

}
