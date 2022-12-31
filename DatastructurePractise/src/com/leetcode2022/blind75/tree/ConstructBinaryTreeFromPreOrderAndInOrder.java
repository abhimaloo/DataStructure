package com.leetcode2022.blind75.tree;


/*
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.



Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]


Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
 */
public class ConstructBinaryTreeFromPreOrderAndInOrder {
    private static int preIndex = 0;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        if (preorder.length != inorder.length) {
            return null;
        }

        // step 1 : preOrder is Root , Left,  Right  and InOrder is Left, Root, Right
        // we will pick an element from preOrder in sequence and find the element in inOrder. Now subarray left to the
        // inorder array's index would form a left subtree and right subArray would form right subtree.
        // Recursively build the tree

        return constructTree(preorder, inorder, 0, inorder.length - 1);

    }

    private static TreeNode constructTree(int[] preOrder, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd || preIndex >= preOrder.length) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preIndex++]);
        int indexInInOrder = find(inorder, inStart, inEnd + 1, root.val);
        root.left = constructTree(preOrder, inorder, inStart, indexInInOrder - 1);
        root.right = constructTree(preOrder, inorder, indexInInOrder + 1, inEnd);
        return root;
    }

    private static int find(int[] inorder, int inStart, int inEnd, int val) {
        while (inStart <= inEnd && inStart >= 0 && inStart < inorder.length) {
            if (inorder[inStart] == val) {
                return inStart;
            }
            inStart++;
        }

        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = buildTree(new int[]{-1}, new int[]{-1});
    }
}
