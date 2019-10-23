package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

/*
https://leetcode.com/problems/path-sum/
 */
public class PathSum {
    public static boolean hasSum = false;

    public static boolean pathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.val == sum) {
            return true;
        }

        return pathSum(root.left, sum - root.val) || pathSum(root.right, sum - root.val);
    }
}
