package com.leetcode2019.medium;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container and n is at least 2.
 */
public class ContainerWithMostWater {
    /**
     * Solution is greedy. Start two pointers - left and right. Keep the max area.
     * move the pointers in on the shorter side to maximize the area
     */
    public static int maxArea(int[] in) {
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = in.length - 1;
        while (left < right) {
            int area = Math.min(in[left], in[right]) * (right - left);
            if (max < area) {
                max = area;
            }

            if (in[left] < in[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
