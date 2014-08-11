package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;

/**
 * Given a binary tree, a target node in the binary tree, and an integer value k,
 * print all the nodes that are at distance k from the given target node. No parent pointers are available.
 *
 * http://www.geeksforgeeks.org/print-nodes-distance-k-given-node-binary-tree/
 * Created by abhishekm787 on 8/11/14.
 */
public class KDistantNodes<T extends Comparable<T>> extends BST<T> {

    /**
     * trick is to maintain a queue ok size k ..
     * whenever you reach target node .. try printing all the nodes down k distance from it..
     * now .. iterate through queue and dequeue one node at a time.. print (k - d) levels down from that node where d
     * is the index of node in the queue ..
     *
     * @param root
     * @param target
     * @param k
     * @param queue
     * @return
     */
    public boolean printKDistantNode(BSTNode<T> root, BSTNode<T> target, int k, LinkedList<BSTNode<T>> queue) {
        if(root != null) {
            if(root == target) {
                // do the counting down logic
                countDownAndPrint(root,target, k);
                int countDown = k;
                while(!queue.isEmpty()) {
                    BSTNode<T> node = queue.remove();
                    countDownAndPrint(node,target, k - countDown);
                    countDown --;
                }
                return true;
            }

            if(queue.size() == k) {
                queue.remove();
            }
            queue.add(root);

            boolean found = printKDistantNode(root.left, target, k,queue);
            if(!found) {
                found = printKDistantNode(root.right, target, k,queue);
            }

            return found;
        }
       return false;
    }

    private void countDownAndPrint(BSTNode<T> root, BSTNode<T> target, int k) {
        if(root != null) {
            if(k == 0 && root != target) {
                System.out.println(root.data);
                return;
            }

            countDownAndPrint(root.left,target, k -1);
            countDownAndPrint(root.right, target,  k -1);
        }
    }

    public static void main(String[] args) {
        KDistantNodes<Integer> tree = new KDistantNodes<>();
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

        tree.printKDistantNode(tree.root, tree.find(tree.root,12),4, new LinkedList<BSTNode<Integer>>());
    }
}
