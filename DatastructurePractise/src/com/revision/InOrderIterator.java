package com.revision;

import com.interview.trees.DistanceBetweenTwoNodes;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by abhimaloo on 9/20/14.
 */
public class InOrderIterator{

   public LinkedList<BSTNode<Integer>> stack = new LinkedList<>();

   public void pushLeftChildren(BSTNode<Integer> root) {
       while(root!= null) {
           stack.push(root);
           root = root.left;
       }
   }

   public InOrderIterator(BSTNode<Integer> root) {
       if(root!=null) {
          pushLeftChildren(root);
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
       pushLeftChildren(result.right);

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

        InOrderIterator itr = new InOrderIterator(tree.root);

        while(itr.hasNext()) {
            System.out.println(itr.next().data);
        }

    }


}
