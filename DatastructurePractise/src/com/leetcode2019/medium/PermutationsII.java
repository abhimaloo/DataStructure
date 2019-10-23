package com.leetcode2019.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/permutations-ii/
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */
public class PermutationsII {

    /*
    Trick is to sort the input Array first
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) return result;
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        permutate(nums, new ArrayList<>(), result, used);
        return result;
    }

    public void permutate(int[] nums, List<Integer> soFar, List<List<Integer>> result, boolean[] used) {
        if (soFar.size() == nums.length) {
            result.add(new ArrayList<>(soFar));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            used[i] = true;
            soFar.add(nums[i]);
            permutate(nums, soFar, result, used);
            used[i] = false;
            soFar.remove(soFar.size() - 1);
            /*
            We are trying to move to the next element which is not same as ith element;
            that way we would eliminate duplicate permutations
             */
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                ++i;
            }
        }
    }

    public static void main(String[] args) {
        PermutationsII obj = new PermutationsII();
        obj.permuteUnique(new int[]{1, 1, 3});
    }
}
