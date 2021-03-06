package com.leetcode2019.easy;

/*
https://leetcode.com/problems/house-robber/submissions/
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.

 */
public class HouseRobber {
    /*
    Trick is to maintain two sums
    sumWithRob and sumWithoutRib
    sumWithRob would be equlvalent to sumWithoutRob of previous element + nums[i]
    sumWithoutRob - > math.max(robSumOfLastElement, NoRobSumOfLastElement);
    finally do a math.max(robSum, noRobSum)
     */
    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int maxSum = nums[0];
        int sumWithRob = nums[0];
        int sumWithoutRob = 0;

        for (int i = 1; i < nums.length; i++) {
            int temp = sumWithRob;

            sumWithRob = sumWithoutRob + nums[i];

            sumWithoutRob = Math.max(temp, sumWithoutRob); // this is important, you need to consider the max of robbing or not robbing the previous house.
            maxSum = Math.max(sumWithRob, sumWithoutRob);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2, 1, 1, 2}));
    }
}
