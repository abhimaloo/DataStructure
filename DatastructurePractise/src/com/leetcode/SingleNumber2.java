package com.leetcode;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Created by abhimaloo on 8/25/14.
 */
public class SingleNumber2 {
    public static int [] input = {1,2,2,2,1,1,3,3,3,4,4,4,5};

    /**
     * trick is to have a freq count mask for 32 bit integer
     * we will count the frequency of set bit in all the elements for every bit position.
     * while counting it we will take a %3, so that any count which is equal to 3 becomes 0.
     * Hence at last count will contain the set bit of the element which has single occurence.
     * finally recreate the number by left shifting every but at its correct position and taking an ||
     * @param input
     * @return
     */
    public static int findSingle(int[] input) {
        // have a 32 bit integer mask
        int[] counts = new int[32];
        int result = 0;
        // for every bit position
        for(int i = 0; i< 32; i++) {
            // iterate over every element
            for( int j = 0; j < input.length; j++) {
                // check if the i'th bit is set in the element or not
                if(((input[j] >> i)&1) == 1) {
                    // update the frequency count by 1 and take module 3
                    counts[i] = (counts[i] + 1) %3;
                }
            }
           // finally counts[] should contain the bit mask of the element with single occurence
            // for every bit position set the value of the frequency count
           result |= counts[i] << i;

        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(findSingle(input));
    }

}
