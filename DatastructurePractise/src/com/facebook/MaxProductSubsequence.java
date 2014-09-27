package com.facebook;

/**
 * Created by abhimaloo on 9/26/14.
 */
public class MaxProductSubsequence {
    public static int [] input = {2,3,-2,4, 4, -2, 0};

    public static void maxProduct(int[] input) {

        int lastMax = input[0];
        int lastMin = input[0];
        int lastMaxStart = 0;
        int lastMinStart = 0;

        int maxProduct = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;

        for(int i = 1; i< input.length; i++) {

            int currentMax = Math.max(lastMax*input[i], Math.max(lastMin*input[i], input[i]));
            int currentMaxStart = lastMaxStart;
            if( currentMax == input[i]) {
                currentMaxStart = i;
            } else if(currentMax == lastMin*input[i]){
                currentMaxStart = lastMinStart;
            }



            int currentMin = Math.min(lastMax*input[i], Math.min(lastMin*input[i], input[i]));
            int currentMinStart = lastMinStart;
            if(currentMin == lastMax*input[i]) {
                currentMinStart = lastMaxStart;
            } else if(currentMin == input[i]) {
                currentMinStart = i;
            }



            if(currentMax > maxProduct){
                maxProduct = currentMax;
                maxLeft = currentMaxStart;
                maxRight =  i;
            }
            lastMax = currentMax;
            lastMin = currentMin;

            lastMaxStart = currentMaxStart;
            lastMinStart = currentMinStart;


        }

        System.out.println("Max Product : "+ maxProduct+ " from Left:"+ maxLeft+ " to "+ maxRight);

    }


    public static void maxProductII(int[] input){

        int max[]  = new int[input.length];
        int maxStart[] = new int[input.length];
        int minStart[] = new int[input.length];
        int min[]  = new int[input.length];

        max[0] = min[0] = input[0];
        maxStart[0] = minStart[0] = 0;

        int maxProduct = Integer.MIN_VALUE;
        int leftMax = -1;
        int rightMax = -1;

        for(int i = 1; i< input.length; i++) {
            max[i] = Math.max(Math.max(max[i-1] * input[i], input[i]), min[i-1] * input[i]);
            maxStart[i] = maxStart[i-1];
            if(max[i] == input[i]) {
                maxStart[i] = i;
            } else if( max[i] == min[i-1] * input[i]) {
                maxStart[i] = minStart[i-1];
            }


            min[i] = Math.min(Math.min(max[i - 1] * input[i], input[i]), min[i - 1] * input[i]);
            minStart[i] = minStart[i-1];
            if(min[i] == input[i]){
                minStart[i] = i;
            } else if(min[i] == max[i-1] * input[i]) {
                minStart[i] = maxStart[i-1];
            }

            if(maxProduct < max[i]){
                maxProduct = max[i];
                leftMax = maxStart[i];
                rightMax = i;
            }

        }

        System.out.println("Max Product : "+ maxProduct+ " from Left:"+ leftMax+ " to "+ rightMax);




    }


    public static void main(String[] args) {
        maxProduct(input);
    }

}
