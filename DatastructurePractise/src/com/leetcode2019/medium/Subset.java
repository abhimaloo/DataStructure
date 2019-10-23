package com.leetcode2019.medium;

import java.util.ArrayList;
import java.util.List;

public class Subset {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    public void backtrack(int[] nums, int idx, List<Integer> soFar, List<List<Integer>> result) {
        if (idx == nums.length) {
            result.add(new ArrayList(soFar));
            return;
        }
        soFar.add(nums[idx]); //chose the item
        backtrack(nums, idx + 1, soFar, result); // go deeper with the element
        soFar.remove(soFar.size() - 1); // unchose the item
        backtrack(nums, idx + 1, soFar, result); // go deeper without chosing it
    }
}
