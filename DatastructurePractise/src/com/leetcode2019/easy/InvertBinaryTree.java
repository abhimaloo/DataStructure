package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

/**
 * https://leetcode.com/problems/invert-binary-tree/
 * Invert a binary tree.
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * Output:
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 */
public class InvertBinaryTree {
    public static TreeNode invert(TreeNode root) {
        if (root == null) {
            return root;
        }

        invert(root.left);
        invert(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(4)
                .setLeft(
                        new TreeNode(2).setLeft(new TreeNode(1)).setRight(new TreeNode(3))
                ).setRight(
                        new TreeNode(7).setLeft(new TreeNode(6)).setRight(new TreeNode(9))
                );
        TreeNode invertedRoot = invert(tree);
    }
}

