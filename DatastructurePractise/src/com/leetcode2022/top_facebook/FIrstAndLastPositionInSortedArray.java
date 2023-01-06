package com.leetcode2022.top_facebook;

import java.util.Arrays;

/*
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
 */
public class FIrstAndLastPositionInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] response = new int[2];
        Arrays.fill(response, -1);
        if (nums == null || nums.length == 0) return response;

        int index = binarySearch(nums, 0, nums.length - 1, target);
        if (index == -1) return response;
        int leftRange = index;
        int rightRange = index;
        while ((leftRange >= 0 && nums[leftRange] == target)) {
            leftRange--;
        }
        while ((rightRange < nums.length && nums[rightRange] == target)) {
            rightRange++;
        }
        response[0] = leftRange + 1;
        response[1] = rightRange - 1;
        return response;
    }

    public int binarySearch(int[] nums, int left, int right, int target) {
        if (left < right) {
            int mid = (right + left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                return binarySearch(nums, mid + 1, right, target);
            } else {
                return binarySearch(nums, left, mid - 1, target);
            }
        } else {
            return -1;
        }
    }

    public int[] searchRangeOptimized(int[] nums, int target) {
        int[] response = new int[2];
        Arrays.fill(response, -1);
        if (nums == null || nums.length == 0) return response;
        response[0] = binarySearch(nums, 0, nums.length - 1, target, true);
        if (response[0] != -1) {
            response[1] = binarySearch(nums, 0, nums.length - 1, target, false);
        }

        return response;
    }

    public int binarySearch(int[] nums, int left, int right, int target, boolean isFirst) {
        if (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                if (isFirst) {
                    if (mid == left || nums[mid - 1] != target) {
                        return mid;
                    }
                    return binarySearch(nums, left, mid - 1, target, isFirst);
                } else {
                    if (mid == right || nums[mid + 1] != target) {
                        return mid;
                    }
                    return binarySearch(nums, mid + 1, right, target, isFirst);
                }
            } else if (nums[mid] < target) {
                return binarySearch(nums, mid + 1, right, target, isFirst);
            } else {
                return binarySearch(nums, left, mid - 1, target, isFirst);
            }
        }

        return -1;
    }


}
