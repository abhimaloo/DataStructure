package com.leetcode2019.medium;

public class JumpGame {
    public static boolean canJump(int[] nums) {
        if (nums.length == 0) return false;
        return canJump(nums, 0, nums.length - 1);
    }

    /*
    backtracking solution is very expensive
     */
    public static boolean canJump(int[] nums, int curIndex, int target) {
        if (curIndex == target) {
            return true;
        }
        if (curIndex < target) {
            for (int i = nums[curIndex]; i >= 1; i--) {
                if (canJump(nums, curIndex + i, target)) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
    Perfect Solution -
    Idea is to start from back. check the first element from back from where we can reach the target.
    IF yes, recursively find if we can reach to this index from the penultimate of the resultant array
     */
    public static boolean canJump(int[] nums, int target) {
        if (target == 0) return true;
        // first find from back; which is the first position from which target can be reaached
        for (int i = target - 1; i >= 0; i--) {
            if (i + nums[i] >= target) {
                return true && canJump(nums, i);
            }
        }
        return false;
    }

    public static boolean canJumpAgain(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canJumpAgain(new int[]{3, 2, 1, 0, 4}));
    }
}
