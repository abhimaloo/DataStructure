package com.interview.arrays.dp;

/**
 * Given a positive integer N, count all possible distinct binary strings of length N such that there are no consecutive 1′s.
 * Input:  N = 2
 * Output: 3
 *  The 3 strings are 00, 01, 10
 * Created by abhishekm787 on 7/18/14.
 */
public class CountNumberOfBinaryStringHavingNoConsecutiveOne {
    public static int countBinaryStringWithoutConsucutiveOnes(int length){
        //array to keep number of strings with i length which ends in 0
        int[] a = new int[length+1];
        //array to keep number of strings with i length which ends in 1
        int[] b = new int[length+1];

        a[1] = 1;
        b[1] = 1;

        for(int i=2; i<length+1;i++){
            // recurrence relationship.. number of strings for length i+1 would be
            // putting a zero in front of a[i-1] (ends with 0) and b[i-1](ends with 1)
            a[i] = a[i-1]+b[i-1];
            // number of strings ending in 1 for length i would be a[i-1]..
            //put a 1 after a string which ends with 0
            b[i] = a[i-1];
        }

        return a[length]+b[length];

    }

    public static void main(String[] args) {
        System.out.println(countBinaryStringWithoutConsucutiveOnes(3));
    }

}
