package com.interview.arrays;

/**
 * Given an array and a value,
 * how to implement a function to remove all instances of that value in place and return the new length?
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * Created by abhimaloo on 8/13/14.
 */
public class RemoveNumbersInArray {
    public static int[] input = {1,3,7,9,2,7,9,1};

    /**
     * very cool trick to copy over numbers.. only copy if target number is different than supplied one
     * @param input
     * @param n
     * @return
     */
    public static int removeNumber(int[] input, int n) {
        // counter for new size
        int i = 0;

        for( int j=0; j<input.length; j++) {
            // if this number at this index is not same as specified one
            if(input[j] != n) {
                // copy and increase i  .. j will be increased at the end of the loop
                input[i++] = input[j];
            }
        }

        return i;
    }

    public static void main(String[] args) {
        System.out.println(removeNumber(input, 7));

    }
}
