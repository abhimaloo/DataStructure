package com.leetcode2019.hard;

import com.datastructure2019.tree.TreeNode;

public class IsSymetric {
    public static boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == root2;
        }

        boolean symetricChild = isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
        return root1.val == root2.val && symetricChild;
    }
}
