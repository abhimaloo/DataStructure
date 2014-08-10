package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Given the binary Tree and the two nodes say ‘a’ and ‘b’, determine whether the two nodes are cousins of each other or not.
 * Two nodes are cousins of each other if they are at same level and have different parents.
                    8
               4        12
            2    6   10    14
          1    5   7

 * http://www.geeksforgeeks.org/check-two-nodes-cousins-binary-tree/
 * Created by abhimaloo on 8/9/14.
 */
public class IsCousinNode<T extends Comparable<T>> extends BST<T> {

    private int findLevel(BSTNode<T> root, BSTNode<T> node, int level) {
        // if you reached null that means node has not been found return 0 level
        if(root == null) {
            return 0;
        }

        // node is found .. return the level
        if(root == node) {
            return level;
        }
        // find node in left subtree.. and increse the level
        int l = findLevel(root.left, node, level +1);
        // if level  = 0 means node has not been found in left subtree
        if(l == 0) {
            // try right subtree ..
            l = findLevel(root.right, node, level +1);
        }
        // finally return the level
        return l ;
    }


    // this method finds the siblings. Siblings means nodes which belong to the same parent
    private boolean isSibling(BSTNode<T> root, BSTNode<T> node1,  BSTNode<T> node2 ) {
        // any node being null will return false
        if(root == null || node1 == null || node2 == null) {
            return false;
        }

        // if root;s left child and right child are both these nodes oor vice versa .. return true
        if((root.left == node1 && root.right == node2) || (root.right == node1 && root.left == node2)) {
            return true;
        }
        // otherwise recurse on left and right subtree
        return isSibling(root.left, node1, node2) ||isSibling(root.right, node1, node2) ;
    }


    /**
     * two nodes are cousins only if their levels are same but their parents are not
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    public boolean isCousin(BSTNode<T> root, BSTNode<T> node1, BSTNode<T> node2) {

        return (findLevel(root, node1,0) == findLevel(root, node2,0)) && !(isSibling(root, node1, node2));
    }

    public static void main(String[] args) {
        IsCousinNode<Integer> tree = new IsCousinNode<>();
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

        System.out.println(tree.isCousin(tree.root, tree.find(tree.root,2),tree.find(tree.root,14)));
    }

}
