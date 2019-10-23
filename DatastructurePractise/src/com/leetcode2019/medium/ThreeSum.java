package com.leetcode2019.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> response = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int low = i + 1;
                int hi = nums.length - 1;

                while (low < hi) {
                    if (nums[i] + nums[low] + nums[hi] == 0) {
                        response.add(Arrays.asList(nums[i], nums[low], nums[hi]));
                        while (low < hi && nums[low] == nums[low + 1]) {
                            low++;
                        }
                        while (low < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        low++;
                        hi--;
                    } else {
                        if (nums[hi] + nums[low] < 0 - nums[i]) {
                            low++;
                        } else {
                            hi--;
                        }
                    }
                }
            }
        }
        return response;
    }

    public static void main(String[] args) {
        int[] input = {0, 0, 0};
        List<List<Integer>> response = threeSum(input);
    }
}
