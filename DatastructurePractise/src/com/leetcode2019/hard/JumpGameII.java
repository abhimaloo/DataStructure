package com.leetcode2019.hard;

/*
https://leetcode.com/problems/jump-game-ii/
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.
 */
public class JumpGameII {
    public static int jump(int[] nums) {
        if (nums.length == 0) return 0;
        int[] minJump = new int[1];
        jump(nums, nums.length - 1, minJump);
        return minJump[0];
    }

    public static void jump(int[] nums, int target, int[] minJump) {
        if (target == 0) {
            return;
        }
        int minLast = 0;
        for (int i = target - 1; i >= 0; i--) {
            // last element through which you can reach to the target
            if (i + nums[i] >= target) {
                minLast = i;
            }
        }
        minJump[0]++;
        jump(nums, minLast, minJump);
    }

    public static void main(String[] args) {
        System.out.println(jump(new int[]{2, 3, 1, 1, 4}));
    }
}
