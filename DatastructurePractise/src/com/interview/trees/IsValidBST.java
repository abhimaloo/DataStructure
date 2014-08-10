package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * A binary search tree (BST) is a node based binary tree data structure which has the following properties.
 • The left subtree of a node contains only nodes with keys less than the node’s key.
 • The right subtree of a node contains only nodes with keys greater than the node’s key.
 • Both the left and right subtrees must also be binary search trees.

 http://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
 * Created by abhimaloo on 8/9/14.
 */
public class IsValidBST<T extends Comparable<T>> extends BST<T> {

    /**
     * trick is to check three properties ..
     * a) node's data lies between the range of min and max
     * b) node's left child should be smaller than node's data
     * c) node's right child should be greater
     *
     * recurse on the tree , while setting the right min and max .
     * if you are going to the left child  - setup up Max ..because none of the element under left subtree will be greater than root
     * while moving to right child, set the minimum to the root node's data, since no one is right subtree would be smaller than root node
     * @param root
     * @param min
     * @param max
     * @return
     */
    public boolean isValidBST(BSTNode<T> root, T min, T max) {

        // empty subtree is a valid BST
        if(root == null) {
            return true;
        }

        // check if root's data is between the range of max and min or not
        if(root.data.compareTo(min) < 0 || root.data.compareTo(max)  > 0) {
            return false;
        }

        // left child should be smaller than root and right child should be bigger
        if((root.left!= null && root.left.data.compareTo(root.data) > 0) ||(root.right!= null && root.right.data.compareTo(root.data) < 0)  ){
           return false;
        }

        // both left subtree and right subtree should not break this invariant
        // noone in left subtree should be greater than root hence set max while going to left ..
        // none in right should be smaller than the root, hence set min while going to the right
        return isValidBST(root.left, min, root.data) && isValidBST(root.right, root.data, max);

    }


    public static void main(String[] args) {
        IsValidBST<Integer> tree = new IsValidBST<>();
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

        System.out.println(tree.isValidBST(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE));

    }
}
