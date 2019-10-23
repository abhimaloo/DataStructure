package com.leetcode2019.medium;

/*
https://leetcode.com/problems/find-peak-element/
 */
public class FindPeakElement {
    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        return findPeak(nums, 0, nums.length - 1);
    }

    public static int findPeak(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }

        int mid = (lo + hi) / 2;
        int left = mid == 0 ? Integer.MIN_VALUE : nums[mid - 1];
        int right = mid == nums.length - 1 ? Integer.MIN_VALUE : nums[mid + 1];

        if (nums[mid] > left && nums[mid] > right) {
            return mid;
        } else if (left <= right) {
            return findPeak(nums, mid + 1, hi);
        } else {
            return findPeak(nums, lo, mid - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2}));
    }
}
