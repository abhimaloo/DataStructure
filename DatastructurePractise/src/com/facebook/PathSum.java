package com.facebook;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class PathSum {

    public static boolean printRootToLeafSum(BSTNode<Integer> root, int sum, int targetSum) {

        if(root == null) {
            return false;
        }

        sum+= root.data;

        if(root.left == null && root.right == null) {
            if(sum == targetSum){
                return true;
            }

            return false;
        }

        return printRootToLeafSum(root.left, sum, targetSum) || printRootToLeafSum(root.right, sum, targetSum);
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(tree.root, 8);
        tree.insert(tree.root, 4);
        tree.insert(tree.root, 12);
        tree.insert(tree.root, 2);
        tree.insert(tree.root, 6);
        tree.insert(tree.root, 10);
        tree.insert(tree.root, 14);
        System.out.println(printRootToLeafSum(tree.root, 0, 18));
    }

 }
