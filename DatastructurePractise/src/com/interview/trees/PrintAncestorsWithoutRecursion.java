package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Given a Binary Tree and a key, write a function that prints all the ancestors of the key in the given binary tree.
 * Created by abhimaloo on 8/12/14.
 */
public class PrintAncestorsWithoutRecursion<T extends Comparable<T>> extends BST<T> {

    /**
     * trick is to do level order traversal(BFS) and keep saving parent-child data in a map
     * once we reach target,   print all of the data collected in map
     * @param root
     * @param target
     */
    public void printAncestors(BSTNode<T> root, BSTNode<T> target) {
        Map<T,T> parentMap = new HashMap<>();
        LinkedList<BSTNode<T>> queue = new LinkedList<>();
        queue.add(root);
        parentMap.put(root.data, null);
        while(!queue.isEmpty()) {
            BSTNode<T> node = queue.remove();
            if(node == target) {
                break;
            }


            if(node.left!=null) {
                queue.add(node.left);
                parentMap.put(node.left.data, node.data);
            }

            if(node.right!=null) {
                queue.add(node.right);
                parentMap.put(node.right.data, node.data);

            }

        }

        while(true) {
            if(parentMap.get(target.data) == null) {
                break;
            }
            System.out.println(parentMap.get(target.data));
            target.data = parentMap.get(target.data);
        }

    }

    public static void main(String[] args) {
        PrintAncestorsWithoutRecursion<Integer> tree = new PrintAncestorsWithoutRecursion<>();
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

        tree.printAncestors(tree.root,tree.find(tree.root,1));

    }

}
