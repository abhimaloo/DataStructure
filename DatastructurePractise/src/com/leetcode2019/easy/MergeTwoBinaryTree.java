package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

public class MergeTwoBinaryTree {
    public static TreeNode mergeTwoBinaryTree(TreeNode first, TreeNode second) {
        if (first == null || second == null) {
            return first != null ? first : second;
        }

        TreeNode root = new TreeNode(first.val + second.val);

        root.left = mergeTwoBinaryTree(first.left, second.left);
        root.right = mergeTwoBinaryTree(first.right, second.right);
        return root;
    }


}
