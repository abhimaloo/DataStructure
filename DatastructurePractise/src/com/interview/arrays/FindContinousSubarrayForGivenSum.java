package com.interview.arrays;

/**
 * Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.
 * Example - Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
 * Output: Sum found between indexes 2 and 4
 * Created by abhimaloo on 7/24/14.
 */
public class FindContinousSubarrayForGivenSum {
    public static int[] input = {1, 4, 20, 3, 10, 5};

    /**
     * trick is to keep a running sum and track start and end pointer
     * keep adding elements to running sum till it is less than desired sum
     * if it reaches desired sum, return left and i
     * if it exceeds. start removing element from left till it again either becomes equal or less than desired sum
     * @param input
     * @param sum
     */
    public static void findSubarrayWithSum(int[] input, int sum) {

        int runSum = 0;
        int left = 0;

        for(int j=0; j< input.length;) {
            if(runSum < sum){
                //add to cuumulative total and increment j
                runSum += input[j++];
            }
            if(runSum == sum) {
                System.out.println("Subarray start from :"+left+" to "+ (j-1));
                break;
            }
            if(runSum>sum){
                //reduce left element from cumulative total and move the left to next element
                runSum -= input[left++];
            }
        }

    }

    public static void main(String[] args) {
        findSubarrayWithSum(input,33);
    }
}
