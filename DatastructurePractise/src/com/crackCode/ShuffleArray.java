package com.crackCode;

import java.util.Random;

/**
 * Created by abhimaloo on 9/15/14.
 */
public class ShuffleArray {
    public static int[] input = {1,2,3,4,5,6};

    public static void shuffle(int[] input, int n) {

        for( int i = n; i>=1; i--) {
            Random rand = new Random();
            int t =  rand.nextInt(i);
            //swap the t'th element with ith element
            int temp = input[t];
            input[t] = input[i];
            input[i] = temp;
        }
    }

    public static void main(String[] args) {
        shuffle(input, input.length-1);
        for( int i : input) {
            System.out.print(" " + i + " ");
        }


    }
}
