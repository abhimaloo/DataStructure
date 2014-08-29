package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * https://oj.leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Created by abhimaloo on 8/27/14.
 */
public class ConstructBinaryTreeFromPreOrderAndInOrder {

    public static int[] inorder = {1,2,3,4,5,6,7};
    public static int[] preorder = {4,2,1,3,6,5,7};
    public static int preOrderIndex = 0;

    /**
     * trick is to iterate through preOrder array one by one. create an node with data.
     * populate its left and right child by recursively calling the same method with inorder subArray
     *
     *
     *
     * @param inorder
     * @param preorder
     * @param inorderStart
     * @param inorderEnd
     * @return
     */
    public static BSTNode<Integer> constructTree(int[] inorder, int[] preorder, int inorderStart, int inorderEnd) {
        if(inorderStart > inorderEnd) {
            return null;
        }

        BSTNode<Integer> preOrderNode = new BSTNode<>(preorder[preOrderIndex++], null, null);

        int inorderIndex = binarySearch(inorder, inorderStart, inorderEnd, preOrderNode.data);

        preOrderNode.left = constructTree(inorder, preorder, inorderStart, inorderIndex -1);
        preOrderNode.right = constructTree(inorder, preorder, inorderIndex+1, inorderEnd);

        return preOrderNode;
    }

    private static int binarySearch(int[] inorder, int inorderStart, int inorderEnd, Integer data) {
        if(inorderStart > inorderEnd) {
            return -1;
        } else {

            int mid = (inorderStart + inorderEnd) / 2;
            if(inorder[mid] == data) {
                return mid;
            } else if(inorder[mid] < data) {
                return  binarySearch(inorder, mid+1, inorderEnd, data);
            } else {
                return  binarySearch(inorder, inorderStart, mid-1, data);
            }

        }
    }


    public static void main(String[] args) {
        BSTNode<Integer> root = constructTree(inorder, preorder, 0, inorder.length-1);
        BST<Integer> tree = new BST<>();
        tree.postOrder(root);
    }


}
