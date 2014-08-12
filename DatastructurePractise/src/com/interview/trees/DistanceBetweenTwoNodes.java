package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;

/**
 * Find the distance between two keys in a binary tree, no parent pointers are given.
 * Distance between two nodes is the minimum number of edges to be traversed to reach one node from other.
 * Created by abhimaloo on 8/11/14.
 */
public class DistanceBetweenTwoNodes<T extends Comparable<T>> extends BST<T> {

    /**
     * trick is to maintain a queue of every node coming in the way till you reach either of the one node
     * whenever you reach target node .. try printing all the nodes down k distance from it..
     * now .. iterate through queue and dequeue one node at a time.. print (k - d) levels down from that node where d
     * is the index of node in the queue ..
     *
     * @param root
     * @param queue
     * @return
     */
    public boolean printDistance(BSTNode<T> root, BSTNode<T> node1, BSTNode<T> node2, LinkedList<BSTNode<T>> queue) {
        if(root != null) {
            if(root == node1 || root == node2) {
                // do the counting down logic
                BSTNode<T> target = null;
                BSTNode<T> source = null;

                if(root == node1) {
                   target = node2;
                   source = node1;
                } else {
                    target = node1;
                    source = node2;
                }

                if(!findOtherAndPrint(root,target,source,0)) {
                    while(!queue.isEmpty()) {
                        BSTNode<T> node = queue.remove();
                        if (findOtherAndPrint(node,target, source,queue.size()+1)) {
                            return true;
                        }
                    }
                }
            }

            queue.add(root);

            boolean found = printDistance(root.left, node1, node2,queue);
            if(!found) {
                found = printDistance(root.right, node1, node2, queue);
            }
            return found;
        }
        return false;
    }

    private boolean findOtherAndPrint(BSTNode<T> root, BSTNode<T> target, BSTNode<T> source, int distance) {
        if(root != null) {

            if(root != source && root == target) {
                System.out.println(distance);
                return true;
            }

            if(!findOtherAndPrint(root.left,target,source, distance +1)) {
                return findOtherAndPrint(root.right, target, source, distance +1);
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        DistanceBetweenTwoNodes<Integer> tree = new DistanceBetweenTwoNodes<>();
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
        tree.insert(tree.root,3);

        tree.printDistance(tree.root, tree.find(tree.root,1),tree.find(tree.root,4), new LinkedList<BSTNode<Integer>>());
    }
}
