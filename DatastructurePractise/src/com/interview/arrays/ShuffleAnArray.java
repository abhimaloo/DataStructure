package com.interview.arrays;

/**
 * Given an array, write a program to generate a random permutation of array elements.
 * This question is also asked as “shuffle a deck of cards” or “randomize a given array”.
 * ex - {1,2,3,4,5,6,7,8}  , shiffled array could be {2,6,5,7,8,4,3,1}
 * Created by abhishekm787 on 7/22/14.
 */
public class ShuffleAnArray {
    public static int [] input = {1,2,3,4,5,6,7,8};

    /**
     * trick is to pick a random number between 0 to i(where i decreases from n-1 to 0)
     * swap the randomly generated index with i'th indexed value
     * @param input
     * @param n
     */
    public static void shuffle(int[] input, int n){
        //we do not need to run it for the first element
        for( int i=n-1;i>0; i--){
           //generate random number in the range of 0 to n-1
           int randomNum = 0 + (int)(Math.random()*i);

           //swap ith element with randomly picked element
           int temp = input[randomNum];
           input[randomNum] = input[i];
           input[i] = temp;

       }
    }

    public static void main(String[] args) {
        shuffle(input,input.length);
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);

        }
    }
}
