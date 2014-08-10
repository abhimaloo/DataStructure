package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;


/** Given a binary tree, print out the tree in zig zag level order
 * (ie, from left to right, then right to left for the next level and alternate between).
 * Output a newline after the end of each level.
 *
 * http://leetcode.com/2010/09/printing-binary-tree-in-zig-zag-level_18.html
 * Created by abhimaloo on 8/9/14.
 */
public class PrintBinaryTreeZigZag<T extends Comparable<T>> extends BST<T> {

    /**
     * trick is to use two stacks  -
     * a) Current level stack (at even levels) - Add children in left and right manner
     * b) NextLevelStack (at odd levels) - Add children in right and left manner
     *
     * keep a level indicator called printLevel
     * Do following when print level is even number -
     * a) pop current Level stack and take out the pair object
     * b) print the pair object's node's data
     * c) push left child of pair object to nextLevelStack(odd) and then push right child of pair object to next nextLevelstack(odd)
     * d) check if currentLevel stack is empty or not.. if empty increment the print level .. and change the print line
     *
     * Do following when print level is odd number -
     * a) pop next Level stack and take out the pair object
     * b) print the pair object's node's data
     * c) push right child of pair object to currentLevelStack(even) and then push left child of pair object to next currentLevelstack(even)
     * d) check if next level stack is empty or not.. if empty increment the print level .. and change the print line
     *
     *
     *
     *
     * @param root
     */
    public void printZigZag(BSTNode<T> root) {

        LinkedList<Pair> nextLevelStack = new LinkedList<>(); // this keeps odd level nodes
        LinkedList<Pair> currentLevelStack = new LinkedList<>(); // this keeps even level nodes

        //add the root to even level stack
        currentLevelStack.addFirst(new Pair(root, 0));

        int printLevel = 0;
        // iterate while both are not empty
        while(!currentLevelStack.isEmpty() || !nextLevelStack.isEmpty() ) {
             // for even printLevel
            if(printLevel %2 ==0) {
                // pop even level stack
                Pair p  = currentLevelStack.removeFirst();
                // print the data without new line
                System.out.print(" "+ p.node.data+ " ");
                //push left child to nextLevel(odd level) stack if it is present
                if(p.node.left != null) {
                    nextLevelStack.addFirst(new Pair(p.node.left,p.level+1));
                }
                //push right child to nextLevel(odd level) stack if it is present
                if(p.node.right != null) {
                    nextLevelStack.addFirst(new Pair(p.node.right,p.level+1));
                }
                // check if evenLevel stack is empty .. if yes increment the print level
                if(currentLevelStack.isEmpty()){
                    printLevel ++;
                    // this is for printing level by level in new line
                    System.out.println();
                }

            } else {

                Pair p  = nextLevelStack.removeFirst();

                System.out.print(" "+p.node.data+" ");
                if(p.node.right != null) {
                    currentLevelStack.addFirst(new Pair(p.node.right,p.level+1));
                }
                if(p.node.left != null) {
                    currentLevelStack.addFirst(new Pair(p.node.left,p.level+1));
                }

                if(nextLevelStack.isEmpty()){
                    printLevel ++;
                    System.out.println();
                }

            }
        }

    }


    class Pair {
        public BSTNode<T> node;
        public int level;

        Pair(BSTNode<T> node, int level) {
            this.node = node;
            this.level = level;
        }
    }



    public static void main(String[] args) {
        PrintBinaryTreeZigZag<Integer> tree = new PrintBinaryTreeZigZag<>();
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

        tree.printZigZag(tree.root);
    }

}
