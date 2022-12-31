package com.leetcode2022.blind75.tree;

/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left
subtree
 of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:


Input: root = [2,1,3]
Output: true
Example 2:


Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.


Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
 */
public class ValidateBinarySearchTree {
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    public static boolean isValidBST(TreeNode root, long minValue, long maxValue) {
        if (root == null) {
            return true;
        }

        if (root.val >= maxValue || root.val <= minValue) {
            return false;
        }

        return isValidBST(root.left, minValue, root.val) && isValidBST(root.right, root.val, maxValue);
    }

    public static void main(String[] args) {
        TreeNode s3 = new TreeNode(3);
        TreeNode s4 = new TreeNode(4);
        TreeNode s5 = new TreeNode(5);
        TreeNode s1 = new TreeNode(1);
        TreeNode s6 = new TreeNode(6);

        s5.left = s1;
        s5.right = s4;
        s4.left = s3;
        s4.right = s6;

        System.out.println(isValidBST(s5));

    }
}
