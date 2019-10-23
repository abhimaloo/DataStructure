package com.leetcode2019.hard;

/*
https://leetcode.com/problems/trapping-rain-water/
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

 */
public class TrappingRainWater {
    /*
    Trick is - use two pointers left and right
    maintain left max and right max till both meet.
    now,
    volume at any point would be min(leftmax, rightmax)- height[point].
    even if there are towers in between the amount of water saved there would not change due to that
     */
    public int trap(int[] height) {
        int volume = 0;
        int leftMax = 0;
        int rightMax = 0;

        for (int left = 0, right = height.length - 1; left <= right; ) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                volume += leftMax - height[left++];
            } else {
                volume += rightMax - height[right--];
            }
        }

        return volume;
    }
}
