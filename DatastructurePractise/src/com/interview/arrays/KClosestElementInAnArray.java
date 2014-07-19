package com.interview.arrays;

/**  Given a sorted array arr[] and a value X, find the k closest elements to X in arr[].
 *
 * Input: K = 4, X = 34
 * arr[] = {12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56}
 * Output: 35 30 39 42

 * Created by abhishekm787 on 7/18/14.
 */
public class KClosestElementInAnArray {

    public static int[] input = {12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56};

    /**
     * trick is to use binary search and then merge() of merge sort
     * use binary search for finding the closest element
     * then keep going lft and right and try to find the elements with minimum distance from x
     * @param input   - array
     * @param k - number of closest element
     * @param x - closest number
     * @return
     */
    public static int[] findKClosest(int[] input, int k, int x ){
        //find the crossover index which is less than or equals to the x
        int crossoverIndex = findCrossover(input, x, 0 , input.length-1);
        //declare the result array
        int [] result = new int[k];
        //fill the crossover as first result
        result[0] = input[crossoverIndex];

        int resultAccumulated = 1;
        int indexLeft = crossoverIndex -1;
        int indexRight = crossoverIndex +1;
        // like merge sort
        while(resultAccumulated!=k && indexLeft>=0 && indexRight<input.length){
            //get the closesr distance element from either left or right
            result[resultAccumulated++] =  Math.abs(input[indexLeft]-x) < Math.abs(input[indexRight]-x)? input[indexLeft--]: input[indexRight++];
        }

        while(resultAccumulated<k){
            //put elements from either left or right subarray which ever has capacity left
            result[resultAccumulated++] = indexLeft>=0 ? input[indexLeft--] : input[indexRight++];

        }


        return result;

    }


    /**
     * this subroutine finds the crossover element
     * uses a modified version of binary search
     *
     * @param input
     * @param k
     * @param start
     * @param end
     * @return
     */
    private static int findCrossover(int[] input, int k, int start, int end) {
        if (start>end){
            return -1;
        } else {
            int mid = (start + end)/2;
            //if number to find is between mid and its siblings return this mid point
            // also if midpoint happen to be the last or first element, return the first/last index as midpoint
            if(input[mid] ==k || mid ==input.length-1 || mid==0 ||  (input[mid]<k && input[mid+1]>k) || (input[mid]>k && input[mid-1]<k) ){
                return mid;
            }

            // if x is lesser then left sibling, recurse on left array
            if(input[mid]<k && input[mid+1]<k){
                return findCrossover(input,k,mid+1, end);
            } else {
                // else recurse on right
                return findCrossover(input,k,start, mid -1);
            }

        }
    }


    public static void main(String[] args) {
        findKClosest(input,4,34);
    }
}
