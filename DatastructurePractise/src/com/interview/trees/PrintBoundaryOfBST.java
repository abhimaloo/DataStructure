package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root.
 * For example, boundary traversal of the following tree is “20 8 4 10 14 25 22″
 * Created by abhimaloo on 8/14/14.
 */
public class PrintBoundaryOfBST<T extends Comparable<T>> extends BST<T> {

    /**
     * Trick is to follow these steps -
     * a) print left most boundary wihtout the leaf node (like find min operation)
     * b) print PreOrder(DFS) traversal and print all the leaf nodes only
     * c) print right subtreee while popping out ( find Max , but in reverse order) also not print the root again
     * @param root
     */
    public void printBoundary(BSTNode<T> root) {
        printLeftMostBoundary(root);

        printLeavesLeftToRight(root);

        printRightMostBoundary(root, root);
    }


    private void printRightMostBoundary(BSTNode<T> root, BSTNode<T> notToPrint) {
        if(root!= null) {

            // till we have a not null right child .. keep going to right subtree
            if(root.right != null){
                printRightMostBoundary(root.right, notToPrint);
            }
            // while popping out  -
            // if its not a leaf node and not equal to Root .. print it
            if(!(root.left ==null && root.right == null) && notToPrint!=root) {
                System.out.println(root.data);
            }
        }

    }

    private void printLeavesLeftToRight(BSTNode<T> root) {

        if(root != null) {
            // if its leaf node print it.
            if(root.left ==null && root.right == null) {
                System.out.println(root.data);
            }
            // keep going left
            printLeavesLeftToRight(root.left);
            // keep going right
            printLeavesLeftToRight(root.right);
        }


    }

    private void printLeftMostBoundary(BSTNode<T> root) {

        if(root!= null) {
            // if its not a leaf node print it
            if(!(root.left ==null && root.right == null)) {
                System.out.println(root.data);
            }
             /// keep going to the left subtree. till it reaches leaf node
            if(root.left != null){
                printLeftMostBoundary(root.left);
            }
        }

    }

    public static void main(String[] args) {
        PrintBoundaryOfBST<Integer> tree = new PrintBoundaryOfBST<>();
        tree.insert(tree.root,20);
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);
        tree.insert(tree.root,22);
        tree.insert(tree.root,25);


        tree.printBoundary(tree.root);

    }

}
