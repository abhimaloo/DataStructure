package com.maloo.sorting;

import java.util.Arrays;

/**
 * Created by abhishekm787 on 6/6/14.
 */
public class MergeSort {
    public static int[] input = {4,1,2,8,3,7,5,9};


    public static int[] mergeSort(int[] input) {

        //base case
        if(input.length <= 1) {
            return input;
        }

        //First Half
        int[] leftArray = Arrays.copyOfRange(input, 0, (input.length / 2));
        //Second Half
        int[] rightArray = Arrays.copyOfRange(input, (input.length/2),input.length);

        return merge(mergeSort(leftArray), mergeSort(rightArray));

    }




    private static int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length+right.length];
        int leftHead =0; int rightHead = 0;

        for(int i=0; i<left.length+right.length; i++) {

            if(rightHead < right.length && leftHead<left.length &&  left[leftHead]<right[rightHead]) {
                merged[i] = left[leftHead];
                leftHead++;
            } else if(rightHead < right.length && leftHead<left.length && left[leftHead]>right[rightHead]){
                merged[i] = right[rightHead];
                rightHead ++;
            } else if(leftHead>=left.length) {
                merged[i] = right[rightHead];
                rightHead ++;
            } else if(rightHead >= right.length) {
                merged[i] = left[leftHead];
                leftHead++;
            }

        }

        return merged;
    }

    public static void print(int[] input) {
        for(int i =0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }

    public static void main(String[] args) {

       int[] merged = mergeSort(input);
        System.out.println("After Sort");
        print(merged);
    }


}
