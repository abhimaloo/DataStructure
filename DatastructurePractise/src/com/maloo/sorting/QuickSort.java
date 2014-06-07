package com.maloo.sorting;

/**
 * Created by abhishekm787 on 6/6/14.
 */
public class QuickSort {
    public static int[] input = {4,1,2,8,3,7,5,9};
    //choose a pivot
    // separate elements higher than pivot to right side and lower than pivot to left
    //recursively keep doing this on left array and right array
    public static void quickSort(int[] input, int start, int end) {
        if(start>=end) {
            return;
        } else {
            int pivot = start; //choose a random pivot
            int newPivot = partition(input, start, end, pivot);
            quickSort(input, start, newPivot-1);
            quickSort(input, newPivot+1, end);
        }

    }


    private static int partition(int[] input, int start, int end, int pivot) {
        int i = pivot+1;
        int index = pivot +1;
        for(;index<=end;index++) {

            if(input[index] < input[pivot]) {
                swap(input,i,index);
                i++;
            }

        }
        swap(input,i-1,pivot);
        return i-1;

    }

    private static void swap(int[] input, int indexLeft, int indexRight){
        int temp = input[indexLeft];
        input[indexLeft] = input[indexRight];
        input[indexRight] = temp;

    }

    public static void print(int[] input) {
        for(int i =0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }



    public static void main(String[] args) {

        quickSort(input,0, input.length-1);
        System.out.println("After Sort");
        print(input);
    }

}
