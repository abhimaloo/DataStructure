package com.maloo.divideandconquer;

import com.maloo.sorting.MergeSort;

import java.util.Arrays;

/**
 * Created by abhishekm787 on 6/6/14.
 */
public class DeterministicSelect {
    public static int[] input = {4,1,2,8,3,7,5,9,15,17,13,24,25,26,27,16,18,20,65,66,67,68,69,70,50};


    //finds median of median
    public static int findPivot(int[] input, int sampleSize){

        //If the array has less than or equals to 5 size, sort it and return the median
        if(sampleSize <= 5) {
            int[] out = MergeSort.mergeSort(input);
            return out[sampleSize/2];
        }

        //declare array to hold median
        int[] medians = new int[(sampleSize/5)];

        //make groups of 5 elements and recursive find medians
        for(int i=0; i < medians.length; i ++) {
            int[] inter = Arrays.copyOfRange(input,i*5,(i*5)+5);
            medians[i] = findPivot(inter,5) ;
        }

        //find medians of medians
        return findPivot(medians,medians.length);
    }


    public static void main(String[] args) {
          System.out.print(findPivot(input, input.length));

    }

}
