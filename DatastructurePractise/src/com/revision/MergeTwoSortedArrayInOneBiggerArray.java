package com.revision;

/**
 * Created by abhimaloo on 9/18/14.
 */
public class MergeTwoSortedArrayInOneBiggerArray {
    public static int[] big  = {1,3,5,7,9,0,0,0};
    public static int[] small = {-1,-2,6};

    public static void merge(int[] big, int[] small, int bigSize) {
        int mergeTail = big.length -1;
        int bigTail = bigSize;
        int smallTail = small.length -1;

        while(smallTail >= 0 && bigTail >=0) {
            if(small[smallTail] < big[bigTail]){
                big[mergeTail--] = big[bigTail--];
            } else {
                big[mergeTail--] = small[smallTail--];
            }
        }

        while(smallTail >= 0){
            big[mergeTail--] = small[smallTail--];
        }

    }

    public static void main(String[] args) {
        merge(big, small, 4);
    }

}
