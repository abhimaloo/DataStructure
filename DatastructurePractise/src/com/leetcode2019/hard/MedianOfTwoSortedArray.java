package com.leetcode2019.hard;

/*
https://leetcode.com/problems/median-of-two-sorted-arrays/
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArray {
    /*
    Trick is to do binary search on the shorter array and partition both the arrays in equal number of elements so that
    elements on one side are smaller than elements on other
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] smaller = nums1.length < nums2.length ? nums1 : nums2;
        int[] bigger = nums1.length < nums2.length ? nums2 : nums1;
        int m = smaller.length;
        int n = bigger.length;

        if (m == 0) {
            return n % 2 == 0 ? (double) (bigger[n / 2] + bigger[(n / 2) - 1]) / 2 : bigger[n / 2];
        }

        if (n == 0) {
            return m % 2 == 0 ? (double) (smaller[m / 2] + bigger[(m / 2) - 1]) / 2 : bigger[m / 2];
        }

        int left = 0;
        int right = m;
        double maxLeft = 0;
        double minRight = 0;
        int smallPartition = 0;
        int bigPartition = 0;

        while (left <= right) {
            // find the partition via binary search
            smallPartition = (left + right) / 2;
            bigPartition = (m + n + 1) / 2 - smallPartition;  //left needs more elements if the m + n is odd
            if (smallPartition - 1 >= 0 && bigPartition < n && smaller[smallPartition - 1] > bigger[bigPartition]) {
                right = smallPartition - 1;
            } else if (bigPartition - 1 >= 0 && smallPartition < m && bigger[bigPartition - 1] > smaller[smallPartition]) {
                left = smallPartition + 1;
            } else {
                if (smallPartition == 0) {
                    maxLeft = bigger[bigPartition - 1];
                } else if (bigPartition == 0) {
                    maxLeft = smaller[smallPartition - 1];
                } else {
                    maxLeft = Math.max(smaller[smallPartition - 1], bigger[bigPartition - 1]);
                }
                break;
            }
        }

        if ((m + n) % 2 == 1) {
            return maxLeft;
        }

        if (smallPartition == m) {
            minRight = bigger[bigPartition];
        } else if (bigPartition == n) {
            minRight = smaller[smallPartition];
        } else {
            minRight = Math.min(bigger[bigPartition], smaller[smallPartition]);
        }

        return (double) (minRight + maxLeft) / 2;

    }
}
