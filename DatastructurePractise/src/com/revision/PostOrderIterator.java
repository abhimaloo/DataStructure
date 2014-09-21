package com.revision;

import com.interview.trees.DistanceBetweenTwoNodes;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by abhimaloo on 9/20/14.
 */
public class PostOrderIterator {
    LinkedList<BSTNode<Integer>> stack = new LinkedList<>();

    public void pushTillLeafNode(BSTNode<Integer> root) {
        while(root!= null) {
            stack.push(root);

            if(root.left!=null) {
                root = root.left;
            } else {
                root = root.right;
            }

        }
    }

    public PostOrderIterator(BSTNode<Integer> root) {
        if(root!= null) {
            pushTillLeafNode(root);
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public BSTNode<Integer> next() {
        if(!hasNext()) {
            throw new NoSuchElementException("visited all the elements");
        }

        BSTNode<Integer> result = stack.pop();

        if(!stack.isEmpty() && stack.peek().left == result){
            pushTillLeafNode(stack.peek().right);
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
        tree.insert(tree.root,5);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);

        PostOrderIterator itr = new PostOrderIterator(tree.root);

        while(itr.hasNext()) {
            System.out.println(itr.next().data);
        }

    }

}
