package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

/*
https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:

Input:
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.


Example 2:

Input:
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.
 */
public class SecondMinimumNodeInBinaryTree {
    public static int min = Integer.MAX_VALUE;

    public static int findSecondMinimumValue(TreeNode root) {
        findMin(root);
        return min == root.val ? -1 : min;
    }

    public static void findMin(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;
        TreeNode otherNode = root.val == root.left.val ? root.right : root.left;
        TreeNode sameNode = otherNode == root.left ? root.right : root.left;
        if (otherNode.val != sameNode.val && otherNode.val < min) {
            min = otherNode.val;
        }
        findMin(sameNode);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree(new Integer[]{1, 1, 2, 1, 1, 2, 2, null, null, null, null, null, null, null, null});
        System.out.println(findSecondMinimumValue(root));
    }

}
