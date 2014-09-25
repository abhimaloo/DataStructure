package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Created by abhimaloo on 9/24/14.
 */
public class BSTLevelOrderUsingDFS {

    public static void printLevelOrder(BSTNode<Integer> root) {
      for(int i = 1; i< maxDepth(root); i++) {
          printLevel(root, i);
          System.out.println();
      }

    }

    private static int maxDepth(BSTNode<Integer> root) {
        if(root == null){
            return 1;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) +1;
    }

    public static void printLevel(BSTNode<Integer> root, int level) {
        if(root == null){
            return;
        }

        if(level == 1){
            System.out.print(" " + root.data + " ");
        } else {
            printLevel(root.left, level-1);
            printLevel(root.right, level-1);
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

        printLevelOrder(tree.root);

    }

}
