package com.interview.trees;

/**
 * Created by abhimaloo on 8/9/14.
 */
public class FindDiameter {
    public static int maxDiameter = Integer.MIN_VALUE;

    /**
     * Idea is to go through every node and find out max height of the left sub tree and right subtree..
     * finally calculate diameter at every node by addding leftTRee height + right tree height + 1(root)
     * keep comparing it with maxDiamater and update maxDiameter if necessary ..
     * finally return the max height - (1 + Max(leftHeight, rightHeight))
     *
     * @param root
     * @return
     */
    public static int findDiameter(BSTNode<Integer> root){
        // you you reach the dead end .. return 0
        if(root == null) {
            return 0;
        }

        // leftDepth contains the max height of left subtree
        // recurse on left subtree
        int leftDepth = findDiameter(root.left);
        // right Depth contains the mac height of right subtree
        // recurse on right child
        int rightDepth = findDiameter(root.right);

        //calculate diameter for this root.
        int diameter = leftDepth + rightDepth + 1;

        // update diameter if necessary
        if(maxDiameter < diameter) {
            maxDiameter = diameter;
        }

       // return the max height by including the max length among left subtree chain or right subtree chain
       return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,2);
        tree.insert(tree.root,6);
        //tree.insert(tree.root,10);
        //tree.insert(tree.root,14);
        tree.insert(tree.root,1);
        tree.insert(tree.root,5);
        tree.insert(tree.root,7);

        findDiameter(tree.root);
        System.out.println(maxDiameter);
    }
}
