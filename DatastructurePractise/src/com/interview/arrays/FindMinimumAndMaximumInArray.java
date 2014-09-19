package com.interview.arrays;

/**
 * function to return minimum and maximum in an array. You program should make minimum number of comparisons.
 * ex - arr[] = {1000, 11, 445, 1, 330, 3000}
 * Created by abhimaloo on 7/26/14.
 */
public class FindMinimumAndMaximumInArray {
    public static int [] input = {1000, 11, 445, 1, 330, 3000};

    /**
     * trick is to find in pair. it reduces the number of comparisions
     * @param input
     */
    public static void findMiniMax(int[] input){
        int min = 0;
        int max = 0;
        if(input.length%2 ==0){
            if(input[0] <= input[1]){
                min = input[0];
                max = input[1];
            } else {
                min = input[0];
                max = input[0];
            }
        }

        for(int i=3; i< input.length; i+=2) {
            if(input[i] < input[i-1]){
                if(input[i] < min){
                    min = input[i];
                }
                if(input[i-1] > max){
                    max = input[i-1];
                }
            } else {
                if(input[i-1] < min){
                    min = input[i-1];
                }
                if(input[i] > max){
                    max = input[i];
                }
            }
        }

        System.out.println("Min : "+min+" Max : "+max);

    }

    public static void findminMax(int [] input) {
        int left = 0;
        int right = input.length-1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        while(left <= right) {

            if(left<right) {
                if(input[left] < input[right]) {
                    min = Math.min(input[left], min);
                    max = Math.max(input[right], max);
                } else {
                    min = Math.min(input[right], min);
                    max = Math.max(input[left], max);
                }
                right --;
                left ++;
            } else {
                min = Math.min(input[left], min);
                max = Math.max(input[left], max);
            }
        }


        System.out.println("Minimum :" +  min+ "Max :"+ max);

    }


    public static void main(String[] args) {
        findMiniMax(input);
        findminMax(input);
    }

}
