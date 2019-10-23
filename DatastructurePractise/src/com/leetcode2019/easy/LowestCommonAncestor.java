package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

public class LowestCommonAncestor {
    /*
    Trick is - Go top down -
    if you find either p or q - you return the root. since its top down the case where root can be the p is covered.


     */
    public static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        // not found or null root
        if (root == null) {
            return root;
        }
        // if you find either p or q - you return the root. since its top down the case where root can be the p is covered.
        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        // lets recurse on left subtree  and try finding p or q
        TreeNode left = lca(root.left, p, q);
        // lets recurse on right subtree  and try finding p or q
        TreeNode right = lca(root.right, p, q);

        // the only time we would have found nodes on left and right subtree would be when node itself is polar
        if (left != null && right != null) {
            return root;
        }
        // else we are still quite above the polar point; hence move towards the subtree where we actually found either of the nodes.
        return left == null ? right : left;
    }
}
