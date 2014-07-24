package com.interview.arrays.dp;

/**
 * Given an array arr[0 ... n-1] containing n positive integers,
 * a subsequence of arr[] is called Bitonic if it is first increasing, then decreasing.
 * Write a function that takes an array as argument and returns the length of the longest bitonic subsequence.
 A sequence, sorted in increasing order is considered Bitonic with the decreasing part as empty.
 Similarly, decreasing order sequence is considered Bitonic with the increasing part as empty.

 Input arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
 Output: 6 (A Longest Bitonic Subsequence of length 6 is 1, 2, 10, 4, 2, 1)

 * Created by abhimaloo on 7/24/14.
 */
public class LongestBitonicSubsequence {

    public static int[] input = {1, 11, 2, 10, 4, 5, 2, 1};

    public static int findLongestBitonicSubsequenceLength(int[] input) {

        int[] lis = new int[input.length];
        int[] lds = new int[input.length];

        //default all the lis and lds to length 1 ..
        // since one element itself creates is increasing and decreasing subsequence of length1
        for( int i=0; i< input.length; i++) {
            lis[i] = 1;
            lds[i] = 1;
        }

        //fill in lis
        for( int i =0; i< input.length; i++) {
            for( int j=0; j<i; j++) {
                if(input[j] <= input[i] && lis[j]+1> lis[i]) {
                    lis[i] = lis[j] +1;
                }
            }
        }

        //fill in lds
        for( int i=input.length-1; i>=0; i--){
            for(int j = input.length-1; j>i; j--) {
                if(input[i]>= input[j] && lds[j]+1 > lds[i]){
                    lds[i] = lds[j]+1;
                }
            }
        }

        int maxBitonic = 0;
        //find maximum length of lds[i]+lds[j] -1
        for( int i=0; i< input.length; i++) {
            if(maxBitonic < lis[i]+lds[i] -1){
                maxBitonic = lis[i]+lds[i] -1;
            }
        }

        return maxBitonic;
    }

    public static void main(String[] args) {
        System.out.println(findLongestBitonicSubsequenceLength(input));
    }
}
