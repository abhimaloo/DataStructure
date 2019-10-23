package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

/*
https://leetcode.com/problems/count-complete-tree-nodes/
 */
public class CountCompleteTreeNodes {

    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);
        if (leftDepth == rightDepth) {
            return (int) Math.pow(2, leftDepth) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    public static int leftDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.left;
            depth++;
        }
        return depth;
    }

    public static int rightDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.right;
            depth++;
        }
        return depth;
    }


    public static void main(String[] args) {
        TreeNode tree = new TreeNode(7)
                .setLeft(
                        new TreeNode(3).setLeft(new TreeNode(9)).setRight(new TreeNode(20)
                        )).setRight(
                        new TreeNode(15));
        System.out.println(countNodes(tree));
    }
}
