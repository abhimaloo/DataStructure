package com.facebook;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

 An example is the root-to-leaf path 1->2->3 which represents the number 123.

 Find the total sum of all root-to-leaf numbers.

 For example,

  1
 / \
 2   3
 The root-to-leaf path 1->2 represents the number 12.
 The root-to-leaf path 1->3 represents the number 13.

 Return the sum = 12 + 13 = 25.

 * Created by abhimaloo on 9/27/14.
 */
public class SumRootToLeafNumber {

    public static int sum(BSTNode<Integer> root, Integer number, Integer total) {
        if(root == null) {
            return 0;
        }

        number = number*10 + root.data.intValue();

        if(root.left == null && root.right == null) {
            total += number;
            return total;
        }

        if(root.left!= null) {
         total = sum(root.left, number, total);
        }

        if(root.right != null) {
         total = sum(root.right, number, total);
        }

        return total;
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,9);
        Integer total = new Integer(0);
        int t = sum(tree.root, new Integer(0), total);

        System.out.println(t);

    }


}
