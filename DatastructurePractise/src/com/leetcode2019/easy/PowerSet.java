package com.leetcode2019.easy;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {
    public static List<List<Integer>> powerset(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        backtrack(0, nums, new ArrayList<>(), result);

        return result;
    }

    public static void backtrack(int index, int[] nums, List<Integer> soFar, List<List<Integer>> result) {
        if (index >= nums.length) {
            result.add(new ArrayList<>(soFar));
            return;
        }

        // chose and unchose
        soFar.add(nums[index]);
        backtrack(index + 1, nums, soFar, result);
        soFar.remove(soFar.size() - 1);
        backtrack(index + 1, nums, soFar, result);
    }

    public static void main(String[] args) {
        powerset(new int[]{1, 2, 3});
    }
}
