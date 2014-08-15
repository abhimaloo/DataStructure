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
     * a) print left most boundary without the leaf node (like modified find min operation, instead we have to go right as well)
     * b) print PreOrder(DFS) traversal and print all the leaf nodes only
     * c) print right subtreee while popping out (pass root's right child to the method so that root is not printed again)
     *
     * @param root
     */
    public void printBoundary(BSTNode<T> root) {
        // print left most elements also right childs with no left siblings ..
        printLeftMostBoundary(root);
        // do preOrder and print only the leaves
        printLeavesLeftToRight(root);
        // from rott's right child (to avoid duplicate printing, print right most childs ..
        // also take care of left childs in right subtree which does not have right siblings ..
        printRightMostBoundary(root.right);
    }


    private void printRightMostBoundary(BSTNode<T> root) {
        if(root!= null) {

            // till we have a not null right child .. keep going to right subtree
            if(root.right != null){
                printRightMostBoundary(root.right);
                //print it while popping out
                System.out.println(root.data);
            } else {
                // if you are on the onde which has left subtree but not right subtree then follow to the left
                if(root.left !=  null) {
                    // keep on passing the left child
                    printRightMostBoundary(root.left);
                    // print it while popping out
                    System.out.println(root.data);
                }
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

             /// keep going to the left subtree. till it reaches leaf node
            if(root.left != null){
                System.out.println(root.data);
                printLeftMostBoundary(root.left);

            } else if(root.right != null) {
                // if you reach a node which has no more left child but right child ..
                // proceed to the right child and again look for far left
                System.out.println(root.data);
                printLeftMostBoundary(root.right);
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
