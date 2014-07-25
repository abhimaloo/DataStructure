package com.interview.arrays;

/**
 * Given an unsorted array arr[] and two numbers x and y, find the minimum distance between x and y in arr[].
 * The array might also contain duplicates.
 * You may assume that both x and y are different and present in arr[].
 *
 * Example  -
 * Input: arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, x = 3, y = 6
 * Output: Minimum distance between 3 and 6 is 4.
 * Created by abhimaloo on 7/24/14.
 */
public class MinimumDistanceInAnArray {
    public static int[] input = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};

    /**
     * very neat trick ..
     * keep a prev and prevIndex pointer
     * go from left to right and stop at an item which is either equals to a or b
     * put this item in prev and index in prevIndex
     * Now keep moving to the right.. if you find another item equals to x or y,
     * check whether its same as of prev or not.. if its not the same update the minimum distance if required
     * if prev is same as of this item update the prev to be this index
     * @param input
     * @param x
     * @param y
     * @return
     */
    public static int findMinimumDistance(int []input, int x, int y){
        int prev =-1; int i=0;
        int privIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        //find first item which matches either of x or y
        for(;i<input.length; i++) {
            if(input[i] == x || input[i] ==y) {
                privIndex = i;
                //since we are breaking increase the i
                prev = input[i++];
                break;
            }
        }

        //keep goinf to the right and check if we have anything equal to x or y
        for( ;i<input.length;i++){
            if(input[i] ==x || input[i] ==y) {
                //if it is not equal to prev item
                if(input[i]!=prev){
                    //update the minimum distance if required
                    if(minDistance > i-privIndex) {
                        minDistance = i-privIndex;
                    }
                } else {
                    //make this index as privIndex
                    privIndex = i;
                }
            }
        }

        return minDistance;

    }

    public static void main(String[] args) {
        System.out.println(findMinimumDistance(input, 3, 6));
    }
}
