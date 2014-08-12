package com.interview.trees;

import com.interview.trees.impl.BinaryTree;
import com.interview.trees.impl.BinaryTreeNode;

/**
 * Swap zero value of a node with non-zero value of one of its descendants so that
 * no node with value zero could be parent of node with non-zero.
 *
 * Created by abhishekm787 on 8/12/14.
 */
public class SinkZeros {

    /**
     * trick is to use post order traversal ..
     * try finding a node which has value 0 but either of the children with nonZero value
     * @param root
     */
    public static BinaryTreeNode<Integer> sinkZero(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return root;
        }

        sinkZero(root.left);
        sinkZero(root.right);
        //check if root's data is zero
        if(root.data.compareTo(0) == 0){
            // if either of the child is nonzero  .. run a custom preorder traversal on this root
            if((root.left != null && root.left.data.compareTo(0) != 0) || (root.right != null && root.right.data.compareTo(0) != 0)) {
               customPreOrder(root);
            }
        }

        return root;
    }

    /**
     * typical preOrder format
     * @param root
     */
    private static void customPreOrder(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return ;
        }

        // check if root's data is zero,
        if(root.data.compareTo(0) == 0) {
            // if left child is non zero . swap the value of root and left child and recurse on left subtree (typical preorder)
            if((root.left != null && root.left.data.compareTo(0) != 0)) {
                int temp = root.data;
                root.data = root.left.data;
                root.left.data = temp;
                customPreOrder(root.left);
            } else if((root.right != null && root.right.data.compareTo(0) != 0)) {
                // swap right child and recurse
                int temp = root.data;
                root.data = root.right.data;
                root.right.data = temp;
                customPreOrder(root.right);
            }
        }
    }


    public static void main(String[] args) {
        BinaryTree<Integer> tree  = new BinaryTree<>();
        tree.insertRoot(0);
        tree.root.addLeft(1).addRight(2).left.addLeft(0).left.addLeft(3).addRight(4);
        sinkZero(tree.root);
        System.out.println(tree.root.data);


    }
}
