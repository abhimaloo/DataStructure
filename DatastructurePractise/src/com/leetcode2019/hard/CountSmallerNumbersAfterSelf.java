package com.leetcode2019.hard;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/count-of-smaller-numbers-after-self/
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
 */
public class CountSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> resList = new ArrayList<>();
        int[] indexes = new int[nums.length];
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) indexes[i] = i;
        mergeSort(nums, indexes, 0, nums.length - 1, result);
        for (int num : result) {
            resList.add(num);
        }
        return resList;
    }

    public void mergeSort(int[] nums, int[] indexes, int lo, int hi, int[] result) {
        if (lo >= hi) return;
        int mid = (hi + lo) / 2;
        mergeSort(nums, indexes, lo, mid, result);
        mergeSort(nums, indexes, mid + 1, hi, result);
        merge(nums, indexes, lo, mid, mid + 1, hi, result);
    }

    public void merge(int[] nums, int[] indexes, int l1, int r1, int l2, int r2, int[] result) {
        int[] temp = new int[r2 - l1 + 1];
        int tempIdx = 0;
        int count = 0;
        int start = l1;
        while (l1 <= r1 && l2 <= r2) {
            if (nums[indexes[l1]] > nums[indexes[l2]]) {
                temp[tempIdx++] = indexes[l2++];
                count++;
            } else {
                result[indexes[l1]] += count;
                temp[tempIdx++] = indexes[l1++];
            }
        }

        while (l1 <= r1) {
            result[indexes[l1]] += count;
            temp[tempIdx++] = indexes[l1++];
        }

        while (l2 <= r2) {
            temp[tempIdx++] = indexes[l2++];
        }

        // this is to update the sorted indexes
        for (int i = 0; i < temp.length; i++) {
            indexes[start + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        CountSmallerNumbersAfterSelf obj = new CountSmallerNumbersAfterSelf();
        obj.countSmaller(new int[]{5, 2, 6, 1});
    }
}
