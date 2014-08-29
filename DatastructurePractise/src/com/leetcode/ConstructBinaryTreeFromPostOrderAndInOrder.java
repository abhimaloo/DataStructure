package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Given postorder and inorder traversal of a tree, construct the binary tree.
 *
 * https://oj.leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * Created by abhimaloo on 8/27/14.
 */
public class ConstructBinaryTreeFromPostOrderAndInOrder {

    public static int[] inorder = {1,2,3,4,5,6,7};
    public static int[] postorder = {1,3,2,5,7,6,4};


    /**
     *
     * Post order is little bit trickier than preOrder
     * We will do all the steps as preorder but
     *
     *
     * @param inorder
     *
     * @param inorderStart
     * @param inorderEnd
     * @return
     */
    public static BSTNode<Integer> constructTree(int[] inorder, int[] postorder, int inorderStart, int inorderEnd, int postorderStart,int postorderEnd) {
        if(inorderStart > inorderEnd) {
            return null;
        }
        // read the root from postOrder End
        BSTNode<Integer> postOrderNode = new BSTNode<>(postorder[postorderEnd], null, null);
        // find the root's index in inorder array
        int inorderIndex = binarySearch(inorder, inorderStart, inorderEnd, postOrderNode.data);

        // left child should get the root by subtracting the length of left subtree -1 from the postOrderStart
        postOrderNode.left = constructTree(inorder, postorder, inorderStart, inorderIndex -1, postorderStart, postorderStart + (inorderIndex - inorderStart) - 1);
        // right subtree will be start would be from postOrderStart + length of inorder left subtree and end would be postorder end -1;
        postOrderNode.right = constructTree(inorder, postorder, inorderIndex+1, inorderEnd, postorderStart + (inorderIndex - inorderStart), postorderEnd -1);

        return postOrderNode;
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
        BSTNode<Integer> root = constructTree(inorder, postorder, 0, inorder.length-1, 0, postorder.length -1);
        BST<Integer> tree = new BST<>();
        tree.inOrder(root);
    }
}
