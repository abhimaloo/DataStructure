package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

 An example is the root-to-leaf path 1->2->3 which represents the number 123.

 Find the total sum of all root-to-leaf numbers.
  1
 / \
2   3

 The root-to-leaf path 1->2 represents the number 12.
 The root-to-leaf path 1->3 represents the number 13.

 Return the sum = 12 + 13 = 25.

 https://oj.leetcode.com/problems/sum-root-to-leaf-numbers/
 * Created by abhimaloo on 8/26/14.
 */
public class RootToLeafPathSum {

    // trick is doing a DFS while keep accumulating current digits
    //whenever you reach a leaf node.. construct the number and add it in sum
    public static int findSum(BSTNode<Integer> root, String currentNum, int sum) {
        if(root!= null) {
            currentNum += root.data;

            //if leaf node
            if(root.left == null && root.right == null) {
                //construct the number from root to leaf path and add it in running sum
                sum += Integer.parseInt(currentNum);
                return sum;
            } else {
                //dfs code
                if(root.left != null) {
                    sum = findSum(root.left, currentNum, sum);
                }
                // dfs code
                if(root.right != null) {
                    sum = findSum(root.right, currentNum, sum);
                }
                return sum;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(tree.root, 2);
        tree.insert(tree.root, 1);
        tree.insert(tree.root, 3);

        System.out.println(findSum(tree.root, "",0));

    }

}
