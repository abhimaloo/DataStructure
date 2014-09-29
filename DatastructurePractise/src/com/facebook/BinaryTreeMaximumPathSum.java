package com.facebook;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Created by abhimaloo on 9/27/14.
 */
public class BinaryTreeMaximumPathSum {
    static class Pair {
        int maxPath;
        int maxSum;

        Pair(int maxPath, int maxSum) {
            this.maxPath = maxPath;
            this.maxSum = maxSum;
        }

        Pair() {

        }
    }


    public static Pair maxSum(BSTNode<Integer> root) {
        if(root == null) {
            return new Pair(Integer.MIN_VALUE +1000, Integer.MIN_VALUE+1000);
        }

        if(root.left == null && root.right == null) {
            return new Pair(root.data, root.data);
        }

        Pair leftPair = maxSum(root.left);
        Pair rightPair = maxSum(root.right);

        Pair rootPair = new Pair();

        rootPair.maxPath = Math.max(Math.max(leftPair.maxPath, rightPair.maxPath) + root.data, root.data);
        rootPair.maxSum = Math.max(Math.max(leftPair.maxSum, rightPair.maxSum), Math.max(leftPair.maxPath+rightPair.maxPath+root.data, root.data));

        return rootPair;
    }


    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(tree.root,4);
        tree.insert(tree.root,1);
        tree.insert(tree.root,5);
       // tree.insert(tree.root,-1);
        tree.insert(tree.root,3);

        Pair p = maxSum(tree.root);
        System.out.println(p.maxSum);

    }

}
