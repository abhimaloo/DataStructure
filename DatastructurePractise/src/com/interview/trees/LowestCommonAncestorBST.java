package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/** LCA in BST  - Very cool algorithm
 * http://www.fusu.us/2013/06/p3-lowest-common-ancestor-in-binary.html
 * Created by abhimaloo on 8/8/14.
 */
public class LowestCommonAncestorBST<T extends Comparable<T>> extends BST<T> {


    /**
     * trick /idea is to use BST contraint ..
     * Any LCA will have this property ..
     * right child cannot be smaller than the LCA ..
     * left child cannot be greater than LCA
     * apply those principals and keep traversing the tree accordingly to find LCA.
     * @param root
     * @param p
     * @param q
     * @return
     */
    public T findLCA(BSTNode<T> root, T p, T q) {
        if(root == null) {
            return null;
        }
        // calculate the Max among two
        T max  = p.compareTo(q) > 0 ? p :q;
        //calculate the min among two
        T min = p.compareTo(q) < 0 ? p :q;

        // if Max value is smaller than root .. recurse on left subtree
        if(max.compareTo(root.data) < 0){
            return findLCA(root.left,p,q);
        } else if(min.compareTo(root.data) > 0 ) {
            // if Min Value is greater than root .. recurse on right subtree
            return findLCA(root.right, p, q);
        } else {
            // if neither is true .. means if left is not bigger then root and right is not smaller is then root
            return root.data;
        }
    }

    public static void main(String[] args) {
        LowestCommonAncestorBST<Integer> tree = new LowestCommonAncestorBST<>();
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

        System.out.println(tree.findLCA(tree.root, 2,2));

    }

}
