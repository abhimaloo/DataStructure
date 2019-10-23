package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

public class LCABST {
    public static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        TreeNode big = p.val > q.val ? p : q;
        TreeNode small = p.val < q.val ? p : q;
        if (small.val <= root.val && big.val >= root.val) {
            return root;
        } else if (big.val <= root.val && small.val <= root.val) {
            return lca(root.left, p, q);
        } else {
            return lca(root.right, p, q);
        }
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(2)
                .setLeft(
                        new TreeNode(1)
                ).setRight(
                        new TreeNode(3)
                );
        System.out.println(lca(tree, new TreeNode(3), new TreeNode(1)).val);
    }

}
