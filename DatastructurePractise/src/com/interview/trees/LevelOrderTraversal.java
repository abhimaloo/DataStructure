package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;

/**
 * Created by abhimaloo on 8/8/14.
 */
public class LevelOrderTraversal<T extends Comparable<T>> extends BST<T> {

    public void levelOrder(BSTNode<T> root) {
        LinkedList<BSTNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            BSTNode<T> node = queue.remove();
            System.out.println(node.data);
            if(node.left != null){
                queue.add(node.left);
            }

            if(node.right != null) {
                queue.add(node.right);
            }
        }
    }

    /**
     * Its level order traversal only but we need to print the data in one line for each level
     * @param root
     */
    public void levelOrderPrintInSameLine(BSTNode<T> root) {

        LinkedList<BSTNode<T>> queue = new LinkedList<>(); // this queue is to keep BST nodes
        LinkedList<Integer> levelQueue = new LinkedList<>(); // this queue is to keep levels

        queue.add(root);  //add root node
        levelQueue.add(0); // add 0'th level

        int printLevel = 0;  //this will keep the printing order correct
        while(!queue.isEmpty() && !levelQueue.isEmpty()) {
            //deque the item from queue
            BSTNode<T> node = queue.remove();
            // deque the level from level queue
            Integer level = levelQueue.remove();

            if (printLevel != level) {
                System.out.println();  // if levels are changing change to next line
                printLevel = level; // update the print level to next line
            }

            System.out.print(" " + node.data + " "); // print the data in current line

            //standard BFS thing .. add children with one level more
            if(node.left != null){
                queue.add(node.left);
                levelQueue.add(level+1);
            }

            if(node.right != null) {
                queue.add(node.right);
                levelQueue.add(level+1);
            }

        }

    }



    public static void main(String[] args) {
        LevelOrderTraversal<Integer> tree = new LevelOrderTraversal<>();
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


        tree.levelOrderPrintInSameLine(tree.root);
    }

}
