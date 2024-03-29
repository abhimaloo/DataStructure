package com.leetcode2022.top_facebook;

/*
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.



Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
Example 2:

Input: nums = [0]
Output: [0]


Constraints:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1


Follow up: Could you minimize the total number of operations done?
 */
public class MoveZeroes {
    /*
    Intuition : keep a counter of how many zeros have we seen so far.
    now - whenever you see a non-zero element, check if you have seen the zero before
     */
    public static void moveZeroes(int[] nums) {
        int snowball = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                snowball++;
            } else if (snowball > 0) {
                // swap the element with the leftmost zero
                nums[i - snowball] = nums[i];
                nums[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 2, 3, 1};
        moveZeroes(nums);
    }
}
