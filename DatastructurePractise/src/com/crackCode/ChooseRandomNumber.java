package com.crackCode;

import java.util.Random;

/**
 * Created by abhimaloo on 9/16/14.
 */
public class ChooseRandomNumber {

    public static int[] input = {1,2,3,4,5,6,7,8,9,10};
    public static int[] chooseRandom(int[] input, int m) {
        int[] random = new int[m];

        for(int i = 0; i< m; i++) {
            //chose a random number between i and input.length-1
            Random rand = new Random();
            // choose random number between i and input.length -1 inclusive
            int target = i + rand.nextInt(input.length-1 - i +1);
            //pick and save the random number
            random[i] = input[target];
            //swap target element with ith element
            int temp = input[i];
            input[i] = input[target];
            input[target] = temp;
        }

        return random;
    }


    public static void main(String[] args) {

        for(int i : chooseRandom(input, 5)) {
            System.out.println(i);
        }



    }
}
