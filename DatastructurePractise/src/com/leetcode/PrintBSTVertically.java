package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Given a binary tree, print it vertically. The following example illustrates vertical order traversal.

           1
         /   \
        2     3
       / \    / \
      4   5  6   7
              \   \
               8   9


 The output of print this tree vertically will be:
 4
 2
 1 5 6
 3 8
 7
 9
 * Created by abhimaloo on 9/24/14.
 */
public class PrintBSTVertically {


    public static void getHorizontalDistance(BSTNode<Integer> root, int horizontalDistance, Map<Integer, List<BSTNode<Integer>>> distanceMap) {

        if(root == null){
            return;
        }

        if(!distanceMap.containsKey(horizontalDistance)){
            distanceMap.put(horizontalDistance, new ArrayList<BSTNode<Integer>>());
        }
        distanceMap.get(horizontalDistance).add(root);

        getHorizontalDistance(root.left, horizontalDistance - 1, distanceMap);
        getHorizontalDistance(root.right, horizontalDistance + 1, distanceMap);

    }

    public static void printVerticalTree(BSTNode<Integer> root) {
        Map<Integer, List<BSTNode<Integer>>> distanceMap = new TreeMap<>();
        getHorizontalDistance(root, 0, distanceMap);
        for(Integer level : distanceMap.keySet()) {
            for(BSTNode<Integer> node : distanceMap.get(level)) {
                System.out.print(" " + node.data + " ");
            }
            System.out.println();
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

        printVerticalTree(tree.root);
    }

}
