package com.interview.arrays;

/**
 * Created by abhishekm787 on 7/15/14.
 * An array contains both positive and negative numbers in random order. Rearrange the array elements so that positive and negative numbers are placed alternatively. Number of positive and negative numbers need not be equal. If there are more positive numbers they appear at the end of the array. If there are more negative numbers, they too appear in the end of the array.
 * For example, if the input array is [-1, 2, -3, 4, 5, 6, -7, 8, 9], then the output should be [9, -7, 8, -3, 5, -1, 2, 4, 6]
 */
public class RearrangePositiveAndNegativeNumbers {
    public static int[] input= {-1, 2, -3, 4, 5, 6, -7, 8, 9};

    public static int[] rearrange(int[] input){
       int pivot =0;
       // index of the item greater than pivot
       int maxPivot = 0;

       // it is quick sort's partition logic
       // partition the array around pivot ="0", hence all the negatives will be on the left of maxPivot
        //and positives on the right
       for( int i=0; i< input.length; i++){
            if(input[i]<pivot) {
                int temp = input[maxPivot];
                input[maxPivot] = input[i];
                input[i] = temp;
                maxPivot++;
            }
       }



       // input array now has all the negative numbers before maxPivot
       // and all positive number from maxPivot to end inclusive
       for(int i=0; i<maxPivot; i++){
           if(i%2==0){
               //swap maxPivot with even ordered element
               int temp = input[maxPivot];
               input[maxPivot] = input[i];
               input[i] = temp;
               // shift the max each one at a time
               maxPivot++;
           }
       }


       return input;

    }

    public static void main(String[] args) {
        rearrange(input);
    }
}
