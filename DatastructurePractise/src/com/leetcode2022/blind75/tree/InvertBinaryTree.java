package com.leetcode2022.blind75.tree;

/*
Given the root of a binary tree, invert the tree, and return its root.
all left child should become right child
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}
