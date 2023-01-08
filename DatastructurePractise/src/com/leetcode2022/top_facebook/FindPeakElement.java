package com.leetcode2022.top_facebook;

/*
A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.



Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.


Constraints:

1 <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
nums[i] != nums[i + 1] for all valid i.
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        return findPeak(nums, 0, nums.length - 1);
    }

    /*
    Intuition is : pick a random middle element.
    Now check the element left of it and right of the mid.
    If the element in the mid has a peak value return mid index as peak otherwise
    check if three numbers are in either ascending order - If asceding recurse from mid+1 to high else lo to mid-1
     */
    public int findPeak(int[] nums, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = (lo + hi) / 2;
        int left = mid == 0 ? Integer.MIN_VALUE : nums[mid - 1];
        int right = mid == nums.length - 1 ? Integer.MIN_VALUE : nums[mid + 1];

        if (nums[mid] >= left && nums[mid] >= right) {
            return mid;
        } else if (left <= right) { // increasing hence peak could be on the right
            return findPeak(nums, mid + 1, hi);
        } else { // decreasing hence peak could be on the left
            return findPeak(nums, lo, mid - 1);
        }

    }
}
