package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

/*
https://leetcode.com/problems/balanced-binary-tree/
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.
 */
public class IsBalancedBinaryTree {
    /*
    Trick is to find two things on every node -
    a) difference between maxDepth of left subtree and right subtree should not be more than 1
    b) left subtree and right subtree should be balanced as well since depth can be formed by singular leg.
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1;
    }

    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
