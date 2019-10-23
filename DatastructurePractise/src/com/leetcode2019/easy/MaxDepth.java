package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

public class MaxDepth {
    public static int maxDepth(TreeNode root) {
        return findDepth(root, 1);
    }

    public static int findDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }

        return Math.max(findDepth(root.left, depth + 1), findDepth(root.right, depth + 1));
    }

}
