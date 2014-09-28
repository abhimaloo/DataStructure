package com.facebook;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class BinaryTreeLevelOrderTraversalII {

    public static void printLevel(BSTNode<Integer> root, int level) {
        if(root == null) {
            return;
        }

        if(level == 1) {
            System.out.print(" "+ root.data+" ");
        }
        if(level > 1) {
            printLevel(root.left, level-1);
            printLevel(root.right, level-1);
        }
    }

    public static int height(BSTNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        return Math.max(height(root.left), height(root.right))+1;

    }


    public static void printLevelOrder(BSTNode<Integer> root) {

        if(root == null) {
            return;
        }

        for( int i = height(root); i>=0; i--) {
            printLevel(root, i);
            System.out.println();
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

        printLevelOrder(tree.root);

    }



}
