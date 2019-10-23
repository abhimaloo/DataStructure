package com.leetcode2019.medium;

/*
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/submissions/
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2]
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
 */
public class FindMinInRotatedArray {

    public static int findMin(int[] nums) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        int pivot = binarySearch(nums, 0, nums.length - 1);
        return pivot == -1 ? nums[0] : nums[pivot];
    }

    // Solution is binary search and return the minimum of middle
    public static int binarySearch(int[] nums, int left, int right) {
        if (left < 0 || right > nums.length - 1) return -1;
        if (left <= right) {
            int mid = (right + left) / 2;
            if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
                return mid;
            } else if (nums[left] <= nums[mid] && nums[mid] > nums[right]) {
                return binarySearch(nums, mid + 1, right);
            } else {
                return binarySearch(nums, left, mid - 1);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }
}
