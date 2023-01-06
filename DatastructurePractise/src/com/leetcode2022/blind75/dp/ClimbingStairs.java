package com.leetcode2022.blind75.dp;

/*
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


Constraints:

1 <= n <= 45
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        int[] ways = new int[n + 1];
        ways[0] = 0;
        ways[1] = 1;

        for (int i = 2; i <= n; i++) {
            // this means to reach step n - you could take 1 step from n-1th step or 2 steps from n-2th step.
            // since 1 and 2 are the allowed numbers of step to take; we can add the ways to reach to n-1 and n-2 steps
            ways[i] = ways[i - 1] + ways[i - 2];
        }
        return ways[n];
    }
}
