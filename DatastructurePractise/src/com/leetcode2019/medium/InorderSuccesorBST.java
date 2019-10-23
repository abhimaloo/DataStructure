package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

public class InorderSuccesorBST {
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        if (p.right == null || root == null) {
            return res;
        }

        if (p.right != null) {  // p has immediate right child; return the leftmost element on the right subtree
            TreeNode min = p.right;
            while (min.left != null) {
                min = min.left;
            }
            return min;
        }
        /*
        This is the trick - > for both cases when a node is either a left child or a right child. we need to find a nearest parent
        whose left side this value reside on-
        for Left child as target - > parent will be the immediate parent
        for right child; it would be parent's parent

         */
        while (root != null) {
            if (root.val >= p.val) {
                res = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return res;
    }
}
