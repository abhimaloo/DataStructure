package com.leetcode2022.top_facebook;

/*
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.
 */
public class NextPermutation {
    // quite tricky - https://www.youtube.com/watch?v=6qXO72FkqwM
    /*
    Intuition is - in order to find the next greater permutation of a number;
    -> you would need to find the longest increasing sequence from the end and get to the peak of it.
    This is because the right most elements has the lowest weight. next permutation has to be a number bigger than current. Lets say this index is i
    -> Now from i keep going back and find the first element which is smaller than i; lets call this index j
    -> swapping the index i and j would make sure that a bigger number i goes to a more significant bit position. hence the resultant number would be strictly greater.
    -> Now to generate the next greater element; you should look at the substring at the right of i (swapped with j) and sort it in increasing order.
    this will make sure that the remaining nubmer is the smallest number.
     */

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int i = nums.length - 2;
        // this is the way to find the element on the left of the peak of LIS starting from right
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // lets find j this is the next smaller number starting from the right end than i.
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }

            // swap i with j
            swap(nums, i, j);
        }

        // reverse the string to the right of i
        reverse(nums, i + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
