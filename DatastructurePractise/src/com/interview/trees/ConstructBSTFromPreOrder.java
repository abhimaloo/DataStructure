package com.interview.trees;

import com.interview.trees.impl.BSTNode;

/**
 * Given preorder traversal of a binary search tree, construct the BST.

 For example, if the given traversal is {10, 5, 1, 7, 40, 50}, then the output should be root of following tree.

 * http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
 * Created by abhimaloo on 8/14/14.
 */
public class ConstructBSTFromPreOrder {

    public static int[] input = {10, 5, 1, 7, 40, 50};

    /**
     * trick is  - preOrder has root as its first element and then left subtre and right subtree
     * Steps -
     * a) pass start and end Index
     * b) create a node from start index calling root
     * c) now do a continous search in preOrder array for the element greater than root
     * d) call this element's index as j ..
     * e) Now recursively create left subtree using start and end as  - start+1 and j-1
     * f) recursively create right subtree using j to end indexes.
     * @param preorder
     * @param start
     * @param end
     * @return
     */
    public static BSTNode<Integer> constructBST(int [] preorder, int start, int end) {
        if(start > end) {
            return null;
        }
        // create the root node with start index..
        BSTNode<Integer> root = new BSTNode<>(preorder[start], null,null);
        int j = start +1;
        // find j which is greater than root's index
        for( ; j <= end; j++) {
            if(preorder[j] > preorder[start]) {
                break;
            }
        }
        // recursively create left subtree form start+1 to j
        root.left = constructBST(preorder, start+1, j-1);
        // right subtree from j to end
        root.right = constructBST(preorder, j, end);

        return root;
    }

    public static void preOrder(BSTNode<Integer> root) {
        if(root != null) {
            System.out.println(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void main(String[] args) {
        BSTNode<Integer> tree = constructBST(input, 0, input.length-1);
        preOrder(tree);
    }
}
