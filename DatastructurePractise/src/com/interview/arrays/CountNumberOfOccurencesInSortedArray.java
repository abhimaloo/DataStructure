package com.interview.arrays;

/**
 * Given a sorted array arr[] and a number x,
 * write a function that counts the occurrences of x in arr[].
 * Expected time complexity is O(Logn)
 *
 * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 2
 * Output: 4 // x (or 2) occurs 4 times in arr[]
 *
 * Created by abhimaloo on 7/24/14.
 */
public class CountNumberOfOccurencesInSortedArray {
    public static int[] input = {1, 1, 2, 2, 2, 2, 3,};

    /**
     * trick is to use binary search twice
     * modify the binary search to find first occurence
     * modify the binary search to find last occurence
     * finally return last-first +1
     * @param input
     * @param x
     * @return
     */
    public static int findNumberOfOccurences(int [] input, int x) {
        //find index of first occurence of x
        int firstOccurence = findFirst(input, x, 0, input.length-1);
        if(firstOccurence==-1){
            return 0;
        }
        //reduce the search size, since we know the first occurence
        //find last occurence
        int lastOccurence = findLastOccurence(input,x,firstOccurence, input.length-1);
        if(lastOccurence ==-1){
            return 1;
        }

        return lastOccurence -firstOccurence +1;

    }

    /**
     * neat trick  - modified binary search
     * mid element should be equal to searched number and mid -1 element should be smaller than searched number
     * @param input
     * @param x
     * @param start
     * @param end
     * @return
     */
    private static int findFirst(int[] input, int x, int start ,int end) {
        if(start>end){
            return -1;
        } else {

            int mid = (start+end)/2;
            //taking care of boundary and main condition
            if(mid ==0 | (x > input[mid-1] && x == input[mid] ) ) {
                return mid;
            }
            if(x > input[mid]) {
                return findFirst(input,x,mid+1,end);
            } else {
                return findFirst(input, x, start, mid-1);
            }

        }

    }

    /**
     * neat trick  - modified binary search
     * mid element should be equal to searched number and mid +1 element should be bigger than searched number
     * @param input
     * @param x
     * @param start
     * @param end
     * @return
     */
    private static int findLastOccurence(int[] input, int x, int start ,int end) {
        if(start>end){
            return -1;
        } else{
            int mid = (start+end)/2;
            //taking care of boundary and main condition
            if(mid ==input.length-1 || (x < input[mid+1] && x == input[mid] ) ) {
                return mid;
            }
            if(x >= input[mid]) {
                return findLastOccurence(input,x,mid+1,end);
            } else{
                return findLastOccurence(input, x, start, mid-1);
            }

        }

    }

    public static void main(String[] args) {
        System.out.println(findNumberOfOccurences(input, 2));
    }
}
