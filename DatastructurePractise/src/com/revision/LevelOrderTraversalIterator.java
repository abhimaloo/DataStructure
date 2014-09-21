package com.revision;

import com.interview.trees.DistanceBetweenTwoNodes;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by abhimaloo on 9/20/14.
 */
public class LevelOrderTraversalIterator {

    LinkedList<BSTNode<Integer>> nodeQueue  = new LinkedList<>();


    public LevelOrderTraversalIterator(BSTNode<Integer> root) {
        if(root!=null) {
            nodeQueue.addLast(root);
        }

    }

    public boolean hasNext(){
        return !nodeQueue.isEmpty();
    }

    public BSTNode<Integer> next() {
        if(!hasNext()){
            throw new NoSuchElementException("Visited ALl Nodes");
        }

        BSTNode<Integer> result = nodeQueue.removeFirst();

        if(result.left != null) {
            nodeQueue.addLast(result.left);
        }

        if(result.right != null) {
            nodeQueue.addLast(result.right);
        }

        return result;
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

        LevelOrderTraversalIterator itr = new LevelOrderTraversalIterator(tree.root);

        while(itr.hasNext()){
            System.out.println(itr.next().data);
        }

    }

}
