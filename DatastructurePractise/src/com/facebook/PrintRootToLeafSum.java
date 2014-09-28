package com.facebook;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class PrintRootToLeafSum {

    public static void printRootToLeafSum(BSTNode<Integer> root, int sum) {

        if(root == null) {
            return ;
        }

        sum+= root.data;

        if(root.left == null && root.right == null) {
            System.out.println(sum);
            return;
        }

        if(root.left!=null) {
            printRootToLeafSum(root.left, sum);

        }

        if(root.right != null) {
            printRootToLeafSum(root.right, sum);
        }

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
        printRootToLeafSum(tree.root, 0);
    }
}
