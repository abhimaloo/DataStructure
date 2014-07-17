package com.interview.arrays;

/**
 * Created by abhimaloo on 7/17/14.
 */
public class FindMinimumElementInSortedRotatedArray {
    public static int[] input = {6,7,8,9,1};

    /**
     * trick is a modified binary search
     * find the mid point. if mid point is smaller than previous element
     * return the midPoint else try to find out subarray where rotation has occurred.
     * @param input
     * @param start
     * @param end
     * @return
     */
    public static int findMinimumElement(int[] input, int start, int end){
        if(start>end){
            return -1;
        }

        int mid = (start+end)/2;

        if(input[mid]<input[mid-1]){
            return input[mid];
        }
        //rotation is in left half
        if(input[start] > input[mid]){
          return findMinimumElement(input,start,mid-1);
        } else {
            //rotation is in right half
          return findMinimumElement(input,mid+1,end);

        }

    }

    public static void main(String[] args) {
        System.out.println(findMinimumElement(input,0,input.length-1));
    }

}
