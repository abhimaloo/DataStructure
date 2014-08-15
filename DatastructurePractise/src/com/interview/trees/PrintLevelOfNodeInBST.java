package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Given a Binary Tree and a key, write a function that returns level of the key.
 * Created by abhimaloo on 8/14/14.
 */
public class PrintLevelOfNodeInBST<T extends Comparable<T>> extends BST<T> {

    /**
     * trick is to do preOrder (DFS) ..
     * keep going down a level deep and add 1 to level .. if you find the target return the level
     * @param root
     * @param target
     * @param level
     * @return
     */
    public int levelOfNode(BSTNode<T> root, BSTNode<T> target, int level) {
        if(root == null){
            return 0;
        }
        if(root == target) {
            return level;
        }
        return levelOfNode(root.left, target, level +1) + levelOfNode(root.right, target, level +1);
    }

    public static void main(String[] args) {
        PrintLevelOfNodeInBST<Integer> tree = new PrintLevelOfNodeInBST<>();
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

        System.out.println(tree.levelOfNode(tree.root, tree.find(tree.root,1), 0 ));

    }


}
