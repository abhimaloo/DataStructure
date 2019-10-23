package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

public class HouseRobberIII {
    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = robit(root);
        return Math.max(res[0], res[1]);
    }

    public static int[] robit(TreeNode root) {
        if (root == null) {
            int[] res = {0, 0};
            return res;
        }

        int[] resLeft = robit(root.left);
        int[] resRight = robit(root.right);
        int leftRoot = resLeft[0];
        int leftNoRoot = resLeft[1];
        int rightRoot = resRight[0];
        int rightNoRoot = resRight[1];

        int[] res = new int[2];
        res[0] = leftNoRoot + rightNoRoot + root.val;
        res[1] = Math.max(leftNoRoot, leftRoot) + Math.max(rightRoot, rightNoRoot);
        return res;
    }

    public static void main(String[] args) {
        /*
        TreeNode tree = new TreeNode(3)
                .setLeft(
                        new TreeNode(2).setRight(new TreeNode(3))
                ).setRight(
                        new TreeNode(3).setRight(new TreeNode(1))
                ); */
        TreeNode tree = new TreeNode(4).setLeft(new TreeNode(1).setLeft(new TreeNode(2).setLeft(new TreeNode(3))));
        System.out.println(rob(tree));
    }
}
