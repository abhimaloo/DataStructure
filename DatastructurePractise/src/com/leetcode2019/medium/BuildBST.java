package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

/*
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

 */
public class BuildBST {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0 || inorder.length != preorder.length) {
            return null;
        }

        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        // make a node from the preorder
        TreeNode node = new TreeNode(preorder[preStart]);
        int inorderIdx = find(inorder, inStart, inEnd, node.val);
        if (inorderIdx == -1) {
            return null;
        }

        node.left = buildTree(preorder, inorder, preStart + 1, preEnd, inStart, inorderIdx - 1);
        node.right = buildTree(preorder, inorder, preStart + (inorderIdx - inStart) + 1, preEnd, inorderIdx + 1, inEnd);

        return node;
        // find this node's idx in inorder
    }

    public static int find(int[] inorder, int start, int end, int data) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == data) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] preorder = {1, 2, 3};
        int[] inorder = {1, 2, 3};
        TreeNode root = buildTree(preorder, inorder);
    }
}
