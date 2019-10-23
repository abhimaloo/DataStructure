package com.leetcode2019.medium;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/permutations/
 Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
public class Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) return result;
        permutation(nums, new ArrayList<>(), result);
        return result;
    }

    public static void permutation(int[] nums, List<Integer> soFar, List<List<Integer>> result) {

        if (soFar.size() == nums.length) {
            result.add(new ArrayList<>(soFar));  //  this is important because List getting changed while popping hence
            // copy the stuff over
            return;
        }

        for (int i : nums) {
            if (soFar.contains(i)) {
                continue;
            }
            soFar.add(i);
            permutation(nums, soFar, result);
            soFar.remove(soFar.size() - 1);
        }
    }

    public static void main(String[] args) {
        permute(new int[]{1, 2, 3});
    }
}
