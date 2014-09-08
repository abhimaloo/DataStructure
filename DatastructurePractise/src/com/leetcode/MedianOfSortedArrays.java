package com.leetcode;

/**
 * There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * https://oj.leetcode.com/problems/median-of-two-sorted-arrays/
 * Created by abhimaloo on 9/7/14.
 */
public class MedianOfSortedArrays {

    public static int[] s1 = {1,3,5,7, 9};

    public static int[] s2 = { 4, 8, 12, 16, 20};

    /**
     * trick is .. follow stesp -
     * a) find Median of arr1  = (start1 + end 1) /2
     * b) find median of arr2 = (start2 + end2) / 2
     * c) if(median1 < median2) then look between median1 end1 and start2 to median2
     * d) if (median 1==median2) , return either of them
     * e) if(median 1 > median 2) , search between  start1 to median1 and median2 to end2
     * f) Doo all the steps till arr1 and arr2 does not become arrays of size 2 .
     * g) if arr1 and arr2 are arrays of size 2, find the average of Math.max(start1, start2) and Math.min(end1, end2) for ex 1 3 7 9.. you want to find average of 7 and 9
     *
     * @param s1
     * @param s2
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    public static int findMedian(int[] s1, int []s2, int start1, int end1, int start2, int end2) {

        if(start1 <= end1 && start2 <= end2) {
            if(start1 +1 == end1 && start2 +1 == end2) {

                return (Math.max(s1[start1], s2[start2]) + Math.min(s1[end1], s2[end2])) /2;
            } else {

                int median1 = (end1 + start1)/2;
                int median2 = (end2 + start2)/2;

                if(s1[median1] == s2[median2]) {
                    return s1[median1];
                } else if(s1[median1] < s2[median2]) {
                    return findMedian(s1, s2, median1, end1, start2, median2);
                } else {
                    return findMedian(s1, s2, start1, median1, median2, end2);
                }
            }

        } else {
            return -1;
        }

    }

    public static void main(String[] args) {
        System.out.println(findMedian(s1, s2, 0, s1.length - 1, 0, s2.length - 1));
    }

}
