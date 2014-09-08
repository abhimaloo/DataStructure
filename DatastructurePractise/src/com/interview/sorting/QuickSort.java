package com.interview.sorting;

/**
 * Created by abhishekm787 on 9/4/14.
 */
public class QuickSort {
    public static int [] input = {5,3,1,2,4,6} ;

    public static void sort(int [] input, int start, int end) {
        if(start > end) {
            return;
        }

        // step 1 is choose pivot
        int pivot = start;

        int newPivot = partition(input, start+1, end, pivot);
        //swap pivot element with newPivot -1 the element
        swap(input, pivot, newPivot);

        // divide and conquer
        sort(input, start, newPivot-1);
        sort(input, newPivot+1,end);

    }

    private static int partition(int[] input, int start, int end, int pivot) {

        int firstBig = start;
        for(int i = start; i<=end ; i++) {
           if(input[i] < input[pivot]) {
               swap(input, i, firstBig);
               firstBig ++;
           }
        }

        return firstBig-1;
    }

    private static void swap(int[] input, int a, int b) {
        int temp = input[a];
        input[a]  = input[b];
        input[b] = temp;
    }

    public static void main(String[] args) {
        sort(input, 0, input.length-1);
        for( int i: input) {
            System.out.println(i);
        }
    }
}
