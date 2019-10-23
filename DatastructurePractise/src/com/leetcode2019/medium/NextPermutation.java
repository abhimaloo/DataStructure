package com.leetcode2019.medium;

/*
https://leetcode.com/problems/next-permutation/
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

 */
public class NextPermutation {
    /*
    https://www.youtube.com/watch?v=PYXl_yY-rms
    Quite tricky algorithm -
    3 steps in it -
    a) from right find the element to the left of longest increase subsequence (right to left), call this index k
    b) Now from k, find the element greater than k starting from right most element; once you find this swap both of them
    c) Now you need to reverse the rest of the substring(suffix) after k to make it smallest possible.

     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 0) return;
        int k = nums.length - 2;
        while (k >= 0 && nums[k] > nums[k + 1]) {
            --k;
        }

        if (k == -1) {  // this is a speacial case where the complete string is in descending order; we need to return the smallest possible substring in that case
            reverse(nums, 0, nums.length - 1);
            return;
        }

        for (int i = nums.length - 1; i > k; i--) {
            if (nums[i] > nums[k]) {
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }
        }

        reverse(nums, k + 1, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
