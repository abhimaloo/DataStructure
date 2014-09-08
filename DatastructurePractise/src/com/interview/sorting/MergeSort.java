package com.interview.sorting;

import java.util.Arrays;

/**
 * Created by abhishekm787 on 9/4/14.
 */
public class MergeSort {
    public static int [] input = {5,3,1,2,4,6};

    public static int[] sort(int[] input) {
         if(input!= null && input.length <= 1) {
             return input;
         }

         int[] left = sort(Arrays.copyOfRange(input, 0, input.length/2));

         int[] right = sort(Arrays.copyOfRange(input, input.length/2, input.length));

         return merge(left, right);

    }

    private static int[] merge(int[] left, int[] right) {
        int [] out = new int [left.length + right.length];
        int lefthead = 0;
        int rightHead = 0;
        int outHead = 0;
        while(lefthead <left.length && rightHead < right.length) {
            if(left[lefthead] <= right[rightHead]) {
                out[outHead++] = left[lefthead++];
            } else {
                out[outHead++] = right[rightHead++];
            }
        }

        while(lefthead < left.length) {
           out[outHead++] = left[lefthead++];
        }

        while(rightHead < right.length) {
            out[outHead++] = right[rightHead++];
        }

        return out;
    }


    public static void main(String[] args) {
        for(int i : sort(input)) {
            System.out.println(i);
        }

    }
}
