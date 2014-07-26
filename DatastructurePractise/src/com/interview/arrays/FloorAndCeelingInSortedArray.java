package com.interview.arrays;

/**
 * Given a sorted array and a value x, the ceiling of x is the smallest element in array greater than or equal to x,
 * and the floor is the greatest element smaller than or equal to x.
 * Assume than the array is sorted in non-decreasing order. Write efficient functions to find floor and ceiling of x.
 *
 * ex - let the input array be {1, 2, 8, 10, 10, 12, 19}
 * For x = 5:    floor  = 2,  ceil  = 8
 * Created by abhimaloo on 7/26/14.
 */
public class FloorAndCeelingInSortedArray {
    public static int[] input = {1, 2, 8, 10, 10, 12, 19};

    public static int findCeeling(int[] input, int start, int end, int k) {
       if(k <= input[start]){
           return input[start];
       }
       if(k >= input[end]){
           return -1;
       }

       int mid = (start +end)/2;

       if(input[mid]>=k && input[mid-1]< k){
           return input[mid];
       }

       if(input[mid] > k && input[mid -1] >k){
           return findCeeling(input,start,mid-1,k);
       }

       if(input[mid]<k) {
           return findCeeling(input,mid+1,end,k);
       }


      return -1;

    }


    public static int findFloor(int[] input, int start, int end, int k) {
        if(k < input[start]){
            return -1;
        }
        if(k > input[end]){
            return input[end];
        }

        int mid = (start +end)/2;

        if(input[mid]<=k && input[mid+1] > k){
            return input[mid];
        }

        if(input[mid] > k ){
            return findFloor(input,start,mid-1,k);
        }

        if(input[mid]<k && input[mid+1] < k) {
            return findFloor(input,mid+1,end,k);
        }


        return -1;

    }

    public static void main(String[] args) {
        System.out.println("Ceeling is :"+ findCeeling(input, 0, input.length - 1, 5));
        System.out.println("Floor is :"+ findFloor(input, 0, input.length - 1, 5));
    }


}
