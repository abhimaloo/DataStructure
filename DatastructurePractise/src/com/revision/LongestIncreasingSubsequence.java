package com.revision;

/**
 *  public static int[] a = {10, 22, 9, 33, 21, 50, 41, 60};
 *
 *  find the length of longest increasing subsequence may not be contiguous
 * Created by abhishekm787 on 9/11/14.
 */
public class LongestIncreasingSubsequence {

    public static int[] input = {10, 22, 9, 33, 21, 50, 41, 60};

    /**
     * Trick is to use
     * @param input
     * @return
     */
    public static int findLIS(int[] input) {
       int[] lis = new int[input.length];
       int maxLength = Integer.MIN_VALUE;
       for( int i = 0; i< input.length; i++) {
           lis[i] = 1;
       }

       for( int i = 0; i< input.length; i++) {
           //compare with all elements on left of it, pick the element
           // which is less than current element and have maximum LIS and add 1 to it.
           for( int j = 0; j < i ; j++) {
               // element in left should be smaller and get the one with maximum lis
               if(input[j] < input[i] && lis[j]+1 > lis[i]) {
                   lis[i] = lis[j] +1;    // increase the length by 1
               }
           }
           // keep track of max length
           if(lis[i] > maxLength) {
               maxLength = lis[i];
           }
       }

       return maxLength;

    }

    public static void main(String[] args) {
        System.out.println(findLIS(input));
    }

}
