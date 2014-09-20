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
            //if either of the nodes match with root
            if(root == node1 || root == node2) {
                // do the counting down logic
                BSTNode<T> target = null;
                BSTNode<T> source = null;
                // decide which node is yet to be found
                if(root == node1) {
                   target = node2;
                   source = node1;
                } else {
                    target = node1;
                    source = node2;
                }

                // try finding the other node down in the subtrees of found node ..
                if(!findOtherAndPrint(root,target,0)) {
                    // if not found under the subtree of found node .. go to ancesstors
                    while(!queue.isEmpty()) {
                        BSTNode<T> node = queue.remove();
                        // find the second node in the ancestors while restricting route going through found Node
                        if (findOtherAndPrint(node,target, source,queue.size()+1)) {
                            return true;
                        }
                    }
                }
                return true;
            }

            // add the root to the queue
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
            if(root == source){
                return false;
            }

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

    private boolean findOtherAndPrint(BSTNode<T> root, BSTNode<T> target, int distance) {
        if(root != null) {

            if(root == target) {
                System.out.println(distance);
                return true;
            }

            if(!findOtherAndPrint(root.left,target, distance +1)) {
                return findOtherAndPrint(root.right, target, distance +1);
            }
            return true;
        }
        return false;
    }


    public static int distance = 0;
    public static boolean set1 = false;
    public static boolean set2 = false;

    public BSTNode<T> findDistance(BSTNode<T> root, BSTNode<T> a, BSTNode<T> b) {
        if(root == null) {
            return null;
        }

        if(root == a || root == b){
            if(set1){
               set2 = true;
            } else{
                set1 = true;
            }

            return root;
        }

        BSTNode<T> left = findDistance(root.left, a, b);
        BSTNode<T> right = findDistance(root.right, a, b);

        if(left != null && right != null) {
            set1 = false;
            set2 = false;
            distance += 2;
            System.out.println("Distance :"+ distance);
            return root;
        }

        if(left != null) {
            if(set1 && set2 && (left == a || left == b ) ){
                distance ++;
            }
            return left;
        } else {
            if(set1 && set2 && (right == a || right == b)){
                distance ++;
            }
            return right;
        }

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
        tree.findDistance(tree.root, tree.find(tree.root,2),tree.find(tree.root,12));

        //tree.printDistance(tree.root, tree.find(tree.root,14),tree.find(tree.root,4), new LinkedList<BSTNode<Integer>>());
    }
}
