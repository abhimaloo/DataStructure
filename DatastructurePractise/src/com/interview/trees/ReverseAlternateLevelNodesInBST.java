package com.interview.trees;


import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a Perfect Binary Tree, reverse the alternate level nodes of the binary tree.
 * Created by abhimaloo on 8/9/14.
 */
public class ReverseAlternateLevelNodesInBST<T extends Comparable<T>> extends BST<T> {

    /**
     * trick is to do Inorder traversals and save all the odd level nodes in a linkedList
     * reverse the linked list
     * finally do again an inorder and replace the odd level data with linkedList data one by one
     * @param root
     */
    public void reverseAlternateLevelNodes(BSTNode<T> root) {

        LinkedList<T> nodeList = new LinkedList<>();
        // collect odd level node's data in nodeList
        collectAlternateLevelNodes(root, 0, nodeList);
        // reverse the linkedList
        Collections.reverse(nodeList);
        // replace odd level nodes with linkedlist one by one
        replaceOddLevelNodes(root,0,nodeList);

    }

    /**
     * Typical in-order traversal.
     * @param root
     * @param level
     * @param nodeList
     */
    private void replaceOddLevelNodes(BSTNode<T> root, int level, LinkedList<T> nodeList) {
        if(root!=null) {
            replaceOddLevelNodes(root.left, level+1,nodeList);
            // if this is odd level
            if(level %2 == 1){
                root.data = nodeList.remove();
            }

            replaceOddLevelNodes(root.right, level+1,nodeList);
        }

    }

    /**
     * save odd level nodes in nodeList
     * @param root
     * @param level
     * @param nodeList
     */
    private void collectAlternateLevelNodes(BSTNode<T> root, int level, List<T> nodeList) {

        if(root!=null) {
            collectAlternateLevelNodes(root.left, level+1,nodeList);
            // if this is odd level
            if(level %2 == 1){
               nodeList.add(root.data);
            }

            collectAlternateLevelNodes(root.right, level+1,nodeList);
        }
    }

    public static void main(String[] args) {
        ReverseAlternateLevelNodesInBST<Integer> tree = new ReverseAlternateLevelNodesInBST<>();
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
        tree.insert(tree.root,3);
        tree.insert(tree.root,9);
        tree.insert(tree.root,11);
        tree.insert(tree.root,13);
        tree.insert(tree.root,15);

       // tree.inOrder(tree.root);
        System.out.println(" ------------");
        tree.reverseAlternateLevelNodes(tree.root);
        tree.inOrder(tree.root);
    }


}
