package com.interview.trees;

/**
 * Implementation of a generic BST .. implemented method insert, find, findMin, remove, preOrder, postOrder, inOrder
 * Created by abhimaloo on 8/8/14.
 */
public class BST<T extends Comparable<T>> {
    public BSTNode<T> root = null;

    /**
     * Method to insert data into BST
     * @param root
     * @param data
     * @return
     */
    public BSTNode<T> insert(BSTNode<T> root, T data) {

        if(root == null){
           root = new BSTNode<>(data,null,null);
           //if root of this tree is empty set it
           if(this.root == null){
               this.root = root;
           }
           return root;
        }

        if(root.data.compareTo(data) < 0){
            root.right = insert(root.right,data);
        } else {
            root.left = insert(root.left, data);
        }

        return root;
    }

    public BSTNode<T> find(BSTNode<T> root, T data) {
        //compare it with root's data and return the node
        if(root!=null && root.data.compareTo(data) ==0){
            return root;
        } else if(root!=null && root.data.compareTo(data) < 0){
            //if root is smaller than data .. search the right subtree
            return find(root.right, data);
        } else if(root!=null && root.data.compareTo(data) > 0){
            //if root is bigger than data .. search the left subtree
            return find(root.left, data);
        }

        return null;
    }

    public BSTNode<T> remove(BSTNode<T> root, T data) {

        //if the data equals to the data of the root
        if(root!=null && root.data.compareTo(data) ==0) {
            // if root has no children
            if(root.left == null && root.right == null){
                return null;
            }

            //if root has just 1 children
            // if the root has only right child
            if(root.left == null) {
               return root.right;
            }
            //if the root has only left child
            if (root.right == null) {
                return root.left;
            }

            // if root has 2 childrens ..

            // find left most child of the right subtree
            BSTNode<T> minNode = findMin(root.right);

            //remove this child from right subtree
            root = remove(root, minNode.data);

            //copy the value of minNode into root
            root.data = minNode.data;

        } else if(root!=null && root.data.compareTo(data) < 0){
            //if root is smaller than data .. search the right subtree
            root.right = remove(root.right,data);
        } else if(root!=null && root.data.compareTo(data) > 0){
            //if root is bigger than data .. search the left subtree
            root.left =  remove(root.left,data);
        }

        return root;
    }

    public BSTNode<T> findMin(BSTNode<T> root) {
        if(root.left == null){
            return root;
        } else {
            return findMin(root.left);
        }
    }


    public void preOrder(BSTNode<T> root) {
        if(root!=null) {
            System.out.println(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void inOrder(BSTNode<T> root) {
        if(root!=null) {
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        }
    }

    public void postOrder(BSTNode<T> root) {
        if(root!=null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.data);
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

        tree.remove(tree.root,8);

    }



}
