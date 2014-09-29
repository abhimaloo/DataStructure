package com.interview.arrays.dp;

/**
 * Given an array of integers where each element represents the max number of
 * steps that can be made forward from that element.
 * Write a function to return the minimum number of jumps to reach the end of
 * the array (starting from the first element).
 * If an element is 0, then cannot move through that element.
 *
 * ex - Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 * Output: 3 (1-> 3 -> 8 ->9)
 * Created by abhimaloo on 7/24/14.
 */
public class MinNumberJumpsToReachEnd {
    public static int[] input = {2,3,1,1,4};

    public static int findMinJumps(int[] input) {
        int [] jumps = new int[input.length];
        //base case.. you dont need a jump to reach start point
        jumps[0] = 0;
        //setup base values
        for(int i=1; i< input.length; i++) {
            jumps[i] = 50000;
        }

        for(int i=1; i< input.length; i++) {
            for( int j=0; j<i; j++) {
                if(jumps[j] + input[j]>= i && jumps[i] > jumps[j] ){
                    jumps[i] = jumps[j]+1;
                }
            }
        }

        return jumps[input.length-1];
    }

    public static void main(String[] args) {
        System.out.println(findMinJumps(input));
    }


}
