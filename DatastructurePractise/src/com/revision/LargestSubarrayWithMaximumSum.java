package com.revision;

/**
 * Created by abhimaloo on 9/17/14.
 */
public class LargestSubarrayWithMaximumSum {
   public static int[] input = {-2, -5,4,10,-1,1, 6, -2, -3, 1, 5, -6};

   public static void findlargestSubarray(int[] input) {
       int start = 0;
       int end = 0;
       int maxSum = input[0];
       int sum = input[0];
       int maxEnd = 0;
       int length = 1;

       for(int i = 1; i< input.length; i++) {
           sum += input[i];
           if(sum < 0) {
               sum = 0;
               start = end = i+1;

           } else {
               end ++;
               if(sum > maxSum) {
                   maxSum = sum;
                   length = end - start;
                   maxEnd = end;
               }
           }

       }

       System.out.println(maxEnd-length + ":"+ maxEnd);

   }

    public static void main(String[] args) {
        findlargestSubarray(input);
    }
}
