package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

public class IsValidBST {
    public static boolean isValidBST(TreeNode root) {
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isValid(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.val > min || root.val < max) {
            return false;
        }

        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }


}
