package com.interview.arrays;

/**
 * A majority element in an array A[] of size n is an element that appears more than n/2 times
 * (and hence there is at most one such element).
 *
 * ex - 3 3 4 2 4 4 2 4 4
 * output - 4
 * Created by abhimaloo on 7/26/14.
 */
public class FindMajorityElementInAnArray {
    public static int[] input = {3, 3, 4 ,2 ,4 ,4 ,2 ,4 ,4};

    /**
     * trick is called Moore's voting algorithm
     * keep a count and a majority element index
     * Now start iterating through the array from left to right
     * if you find the index at element i same as element at majority index increment a counter by 1
     * else decrement a counter by 1
     * wherever counter reaches 0 change the majority element to the element becasue of which it reached zero
     * and set the counter to be 1
     *
     * finally return element at majorityIndex.
     *
     *
     * @param input
     * @return
     */
    public static int findMajorityElement(int[] input){
        int majElement = 0;
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            if(input[i] == input[majElement]){
                count ++;
            } else{
                count --;
            }
            if(count == 0){
                majElement = i;
                count = 1;
            }

        }

        return input[majElement];
    }

    public static void main(String[] args) {
        System.out.println(findMajorityElement(input));
    }

}
