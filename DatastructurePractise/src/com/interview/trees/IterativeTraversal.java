package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;

/**
 * Created by abhishekm787 on 8/13/14.
 */
public class IterativeTraversal<T extends Comparable<T>> extends BST<T> {

    //DFS
    public void preOrder(BSTNode<T> root) {
        LinkedList<BSTNode<T>> nodeStack = new LinkedList<>();
        nodeStack.push(root);

        while(!nodeStack.isEmpty()) {
            BSTNode<T> node = nodeStack.pop();
            System.out.println(node.data);
            if(node.right != null) {
                nodeStack.push(node.right);
            }
            if(node.left != null) {
                nodeStack.push(node.left);
            }
        }
    }

    /**
     * trick is to use these steps -
     * a) run an infinite loop
     * b) maintain a current BST node and initialize it to root
     * c) if current node is not null ..
     * d) push the node to left and increment current to current's left.
     * e) once current becomes null, pop one item out of stack .. print it and then go to current = current.right
     * f) keep moving in the infinite loop  untill current stays not null or stack is not empty empty.
     * g) if current becomes null and stack becomes empty .. exit the infinite loop you are done.
     * @param root
     */
    public void inOrder(BSTNode<T> root) {
        // this will keep the ndoes
        LinkedList<BSTNode<T>> nodeStack = new LinkedList<>();
        // a cursor initialized with root
        BSTNode<T> current = root;
        // signal for infinite loop
        boolean done = false;

        while(!done) {
            if(current != null) {
                // piush the root to stack
                nodeStack.push(current);
                // keep going left
                current  = current.left;
            } else {
                // if stack is not empty
                if(!nodeStack.isEmpty()) {
                    //pop one element
                    current = nodeStack.pop();
                    //process it
                    System.out.println(current.data);
                    // go to the right subtree and follow the process
                    current = current.right;
                } else {
                    // of current becomes null and stack becomes empty ..break the loop
                    done  = true;
                }
            }

        }

    }


    /**
     * Post order is more tricky since it has non tail recursion.
     * Simple trick is  to ise two stacks adn do the following
     * we will follow preOrder traversal but in reverse order (Right,left,Root) for this we will use 2 stacks.
     * Steps are -
     * a) push root to the first stack
     * b) loop while stack is not empty
     * c) pop an element from stack and push it to the second stack
     * d) now check if this popped element has a left child or not. if yes push it on stack 1
     * e) push right child of the popped node on stack 1 (if exist)
     * f) keep doing that till stack1 is not empty.
     * g) One the above loop breaks, process the nodes of stack 2 one by one till it becomes empty
     *
     * @param root
     */
    public void postOrder(BSTNode<T> root) {
        // stack 1 - it will keep elements in reverse preOrder
        LinkedList<BSTNode<T>> nodeStack = new LinkedList<>();
        // second stack
        LinkedList<BSTNode<T>> reversePostOrderStack = new LinkedList<>();
        //push the root on the stack
        nodeStack.push(root);

        while(!nodeStack.isEmpty()) {
            //pop the top node
            BSTNode<T> node = nodeStack.pop();
            // push it to stack 2
            reversePostOrderStack.push(node);
            // if left child exist
            if(node.left != null) {
                //push left child to stack
                nodeStack.push(node.left);
            }
            if(node.right != null) {
                // push right child to stack if it exists
                nodeStack.push(node.right);
            }
        }

        //finally process the second stack one by one
        while(!reversePostOrderStack.isEmpty()) {
            System.out.println(reversePostOrderStack.pop().data);
        }



    }


    public static void main(String[] args) {
        IterativeTraversal<Integer> tree = new IterativeTraversal<>();
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

        tree.postOrder(tree.root);

    }

}
