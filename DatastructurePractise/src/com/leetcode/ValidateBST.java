package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Created by abhimaloo on 8/28/14.
 */
public class ValidateBST {

    public static boolean isValidBST(BSTNode<Integer> root, int minValue, int maxValue) {
        if(root == null) {
            return true;
        }

        if(root.data < minValue || root.data > maxValue) {
            return false;
        } else {
            return isValidBST(root.left, minValue, root.data) && isValidBST(root.right, root.data, maxValue);
        }

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

        System.out.println(isValidBST(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE));

    }

}
