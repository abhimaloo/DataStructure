package com.leetcode2019.easy;

/**
 * https://leetcode.com/problems/non-decreasing-array/
 * Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
 * <p>
 * We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).
 * <p>
 * Example 1:
 * Input: [4,2,3]
 * Output: True
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 * Example 2:
 * Input: [4,2,1]
 * Output: False
 * Explanation: You can't get a non-decreasing array by modify at most one element.
 * Note: The n belongs to [1, 10,000].
 */
public class NonDecreasingArray {
    /*
    Solution : This should be true for increasing sequence -  nums[i-1] <= nums[i] <= nums[i+1]
    case 1 : if nums[i] > nums[i+1]
        => case 1.1 if i > 0
            => case 1.1.1 if nums[i+1] < nums[i-1]  ex - 4,5,3
                => nums[i+1] = nums[i]   - make it 4,5,5
            => case 1.1.2  if nums[i+1] > nums[i-1]  ex - 3, 5, 4
                => nums[i] = nums[i+1]
        => increase modification count
    */
    public static boolean canBeNonDecreasing(int[] nums) {
        int modifications = 0;
        if (nums == null) {
            return false;
        }

        for (int i = 0; i < nums.length - 1 && modifications < 2 && nums.length > 2; i++) {
            if (nums[i] > nums[i + 1]) {
                if (i > 0) {
                    if (nums[i - 1] < nums[i + 1]) {
                        nums[i] = nums[i + 1];
                    } else if (nums[i - 1] > nums[i + 1]) {
                        nums[i + 1] = nums[i];
                    }
                }
                modifications++;
            }
        }

        return modifications < 2;
    }

    public static void main(String[] args) {
        System.out.println(canBeNonDecreasing(new int[]{4, 2, 3}));
        System.out.println(canBeNonDecreasing(new int[]{4, 2, 1}));
        System.out.println(canBeNonDecreasing(new int[]{}));
        System.out.println(canBeNonDecreasing(new int[]{1}));
        System.out.println(canBeNonDecreasing(new int[]{1, 1, 1}));
        System.out.println(canBeNonDecreasing(new int[]{2, 3, 3, 2, 4}));
        System.out.println(canBeNonDecreasing(new int[]{3, 2, 3, 2, 4}));
    }

}
