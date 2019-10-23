package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

public class ConvertToBST {
    public static TreeNode sortedArrayToBST(int[] nums) {
        return convertToBST(0, nums.length - 1, nums);
    }

    public static TreeNode convertToBST(int left, int right, int[] nums) {
        //find mid point
        if (left <= right) {
            int midIndex = (right + left) / 2;
            TreeNode node = new TreeNode(nums[midIndex]);
            node.left = convertToBST(left, midIndex - 1, nums);
            node.right = convertToBST(midIndex + 1, right, nums);
            return node;
        }

        return null;
    }

    public static void main(String[] args) {
        int a[] = {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST(a);
    }
}
