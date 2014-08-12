package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Construct Tree from given Inorder and Preorder traversals
 *
 * http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 * Created by abhimaloo on 8/10/14.
 */
public class ConstructTreeFromInorderAndPreorder<T extends Comparable<T>> extends BST<T> {
    public static int preOrderIndex = 0;

    /**
     * idea is to pick a node from preOrder traversal in increasing order and create a tree node ..
     * Now search that index of that node's data in Inorder traversal
     * Now index less than found Inorder Index will be left subtree and index higher than found index would be right subtree
     * recursively call construct tree method to fill in left and right subtree..
     *
     * @param inorder
     * @param preorder
     * @param inOrderStart
     * @param inOrderEnd
     * @return
     */
    public BSTNode<T> constructTree(List<T> inorder, List<T> preorder, int inOrderStart, int inOrderEnd) {

        // terminating condition
        if(inOrderStart > inOrderEnd) {
            return null;
        }

        // pick a node from PreOrder traversal and create a new node for its name
        BSTNode<T> root = new BSTNode<>(preorder.get(preOrderIndex++), null,null);

        //find this root's data in inorder traversal
        int inorderIndex = binarySearch(inorder, inOrderStart, inOrderEnd, root.data);

        // recursively assign left subtree to the inOrder nodes in the left of found Index
        root.left  = constructTree(inorder, preorder, inOrderStart, inorderIndex -1);
        // recursively create right subtree from the in order nodes found in the right of found index
        root.right = constructTree(inorder, preorder, inorderIndex +1 , inOrderEnd);

        return root;
    }

    /**
     * this method is implementing a binary search on inorder data
     * @param inorder
     * @param inOrderStart
     * @param inOrderEnd
     * @param data
     * @return
     */
    private int binarySearch(List<T> inorder, int inOrderStart, int inOrderEnd, T data) {
        if(inOrderStart>inOrderEnd){
            return -1;
        }

        int mid = (inOrderStart + inOrderEnd)/2;
        if(inorder.get(mid).compareTo(data) == 0) {
            return mid;
        }
        if(inorder.get(mid).compareTo(data) < 0) {
            return binarySearch(inorder, mid+1, inOrderEnd, data);
        } else {
            return binarySearch(inorder, inOrderStart, mid-1,data);
        }

    }


    // method used to create preOrder list
    private void preOrder(BSTNode<T> root, List<T> preOrderList) {
        if(root!=null) {
            preOrderList.add(root.data);

            preOrder(root.left, preOrderList);

            preOrder(root.right, preOrderList);
        }

    }

    // methos used to create inOrder list
    private void inOrder(BSTNode<T> root, List<T> inOrderList) {
        if(root!=null) {
            inOrder(root.left, inOrderList);

            inOrderList.add(root.data);

            inOrder(root.right, inOrderList);
        }

    }


    public static void main(String[] args) {

        ConstructTreeFromInorderAndPreorder<Integer> tree = new ConstructTreeFromInorderAndPreorder<>();
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
        List<Integer> inOrderList = new ArrayList<>();
        tree.inOrder(tree.root, inOrderList);

        List<Integer> preOrderList = new ArrayList<>();
        tree.preOrder(tree.root, preOrderList);



        BSTNode<Integer> root = tree.constructTree(inOrderList,preOrderList, 0, 9);
        tree.inOrder(root);

    }

}
