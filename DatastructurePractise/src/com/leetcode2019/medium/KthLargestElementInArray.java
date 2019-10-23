package com.leetcode2019.medium;

public class KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
    }

    public int findKthLargest(int[] nums, int low, int hi, int k) {
        int pivot = low;
        int newPivotPos = partition(nums, pivot, hi);
        if (newPivotPos == k) {
            return nums[newPivotPos];
        } else if (newPivotPos < k) {
            return findKthLargest(nums, newPivotPos + 1, hi, k);
        } else {
            return findKthLargest(nums, low, newPivotPos - 1, k);
        }
    }

    public int partition(int[] nums, int pivot, int hi) {
        int nextBig = pivot + 1;
        for (int i = nextBig; i <= hi; i++) {
            if (nums[i] < nums[pivot]) {
                swap(nums, nextBig, i);
                nextBig++;
            }
        }

        nextBig--; // this is find the space for pivot to be swapped

        swap(nums, pivot, nextBig);
        return nextBig;
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        KthLargestElementInArray obj = new KthLargestElementInArray();
        System.out.println(obj.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}
