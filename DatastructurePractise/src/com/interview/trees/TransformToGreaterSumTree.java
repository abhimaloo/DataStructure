package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Given a BST, transform it into greater sum tree where each node contains sum of all nodes greater than that node.
 * Created by abhimaloo on 8/9/14.
 */
public class TransformToGreaterSumTree {

    public static int sum = 0;

    /**
     * trick is reverse Inorder  ..
     * go to the right first ..
     * come to the root..
     * go to the left ..
     *
     * while processing the root increment the sum and update the root data.
     * since sum contains the sum of all the greater elements
     * @param root
     */
    public static void transformToGreaterSum(BSTNode<Integer> root) {

        if(root != null) {
            // visit right subtree
            transformToGreaterSum(root.right);
            // update the sum
            sum += root.data;
            // update root's data
            root.data = sum - root.data;
            // now visit left subtree
            transformToGreaterSum(root.left);

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

        transformToGreaterSum(tree.root);
        tree.inOrder(tree.root);



    }

}
