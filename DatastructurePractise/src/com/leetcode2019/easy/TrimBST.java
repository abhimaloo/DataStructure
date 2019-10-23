package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

/*
https://leetcode.com/problems/trim-a-binary-search-tree/
Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.
 */
public class TrimBST {
    /*
    very elegant solution.

     */
    public static TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return root;
        // check if L to R lies on Right side of the root. send the right subtree recursively
        if (root.val < L) {
            return trimBST(root.right, L, R);
        }

        // check if L to R lies on left side of the root. send the left subtree recursively
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }

        // this means L to R lies on both the subtrees
        // trim the left subtree and place it back in the left child
        root.left = trimBST(root.left, L, R);
        // trim the right subtree and place it back in the right child
        root.right = trimBST(root.right, L, R);

        return root;
    }
}
