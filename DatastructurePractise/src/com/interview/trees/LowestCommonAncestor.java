package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/** This is only for binary tree
 * Created by abhimaloo on 8/8/14.
 */
public class LowestCommonAncestor<T extends Comparable<T>> extends BST<T> {

    public BSTNode<T> findLCA(BSTNode<T> root,  T data1, T data2) {

        if(root == null) {
            return root;
        }

        if(root.left !=null && contains(root.left, data1) && contains(root.left, data2)) {
            return findLCA(root.left, data1, data2);
        } else if (root.right !=null && contains(root.right, data1) && contains(root.right, data2)) {
            return findLCA(root.right, data1, data2);
        } else if (root.left!=null && contains(root.left, data1) && root.right !=null && contains(root.right, data2)){
            return root;
        }

        return root;
    }

    public boolean contains(BSTNode<T> root,  T data) {
        if(root == null) {
            return false;
        }

        if(root.data.compareTo(data) == 0){
            return true;
        } else {
           return contains(root.left,data) || contains(root.right, data);
        }
    }

    /**
     * Very tricky to understand .. its a bottom up approach..
     * Modified version of Pre Order traversal
     * Idea is to pre order traversal - root -> left - >right ..
     * the moment you find either one of the nodes ..
     * you should return that root
     *
     * http://www.fusu.us/2013/06/p2-lowest-common-ancestor-in-binary-tree.html
     * @param root
     * @param p
     * @param q
     * @return
     */
    public BSTNode<T> findLCA(BSTNode<T> root, BSTNode<T> p , BSTNode<T> q ) {
        // for null root and unsuccessfull matches
        if(root == null) {
            return null;
        }

        //if you find any of the elements .. return from here
        if(root == p || root == q) {
            return root;
        }

        // find nodes in left subtree
        BSTNode<T> l = findLCA(root.left, p, q);
        // find nodes in right subtree
        BSTNode<T> r = findLCA(root.right, p, q);

        // if the nodes are polar means one on left and other in right ..
        //their will be a node where both l and r be non null .. that would be the LCA node hence return it
        if(l!=null && r!=null) {
            return root;
        }

        // if we have not yet reached to the node where the polarity can be found.. go in the direction where we found
        //at least one node
        return l==null?r:l;
    }


    public static void main(String[] args) {
        LowestCommonAncestor<Integer> tree = new LowestCommonAncestor<>();
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

        System.out.println(tree.findLCA(tree.root,tree.find(tree.root,1),tree.find(tree.root,7)).data);


    }


}
