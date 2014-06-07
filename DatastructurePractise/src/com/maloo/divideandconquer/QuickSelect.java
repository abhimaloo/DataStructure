package com.maloo.divideandconquer;

/**
 * Created by abhishekm787 on 6/6/14.
 */
public class QuickSelect {

    public static int[] input = {4,1,2,8,3,7,5,9};

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

    // kth largest element
    public static int quickSelect(int[] input, int start, int end, int k, int length) {

        if(start>=end) {
            return input[start];
        } else {
            int pivot = start; //choose a random pivot
            int newPivot = partition(input, start, end-1, pivot);
            if(newPivot > (length-k)) {
                return quickSelect(input, start, newPivot-1, k, length);
            } else if(newPivot<(length-k)){
                return quickSelect(input, newPivot+1, end, k , length);
            } else {
                return input[newPivot];
            }


        }

    }

    public static void print(int[] input) {
        for(int i =0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }



    public static void main(String[] args) {

        int kthLargest = quickSelect(input,0, input.length-1,3,input.length);
        System.out.println("Kth Largest Element in : ");
        print(input);
        System.out.println( "is : "+kthLargest);

    }
}
