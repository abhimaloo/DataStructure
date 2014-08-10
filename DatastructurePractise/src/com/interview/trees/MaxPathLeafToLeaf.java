package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Given a binary tree in which each node element contains a number.
 * Find the maximum possible sum from one leaf node to another.
 *
 * http://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
 * Created by abhimaloo on 8/9/14.
 */
public class MaxPathLeafToLeaf {
    public static int maxSum = Integer.MIN_VALUE;

    /**
     * trick is to go to every node and get the Maximum of their left subtree or right subtree.
     * @param root
     * @return
     */
    public static int findMaxPath(BSTNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        int leftPathSum = findMaxPath(root.left);
        int rightPathSum = findMaxPath(root.right);

        int currentSum = Math.max(leftPathSum+rightPathSum+root.data, Math.max(leftPathSum, rightPathSum));
        if(currentSum > maxSum) {
            maxSum = currentSum;
        }

        return Math.max(leftPathSum, rightPathSum) + root.data;
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,2);
        tree.insert(tree.root,6);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);
        tree.insert(tree.root,1);
        tree.insert(tree.root,5);
        tree.insert(tree.root,7);


        findMaxPath(tree.root );
        System.out.println(maxSum);

    }

}
