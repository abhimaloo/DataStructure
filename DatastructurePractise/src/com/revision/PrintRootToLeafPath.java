package com.revision;

import com.interview.trees.DistanceBetweenTwoNodes;
import com.interview.trees.impl.BSTNode;

/**
 * Created by abhimaloo on 9/20/14.
 */
public class PrintRootToLeafPath {
    public static void print(BSTNode<Integer> root, String path) {
        if(root == null){
            return;
        }

        path += root.data;
        if(root.left == null && root.right == null){
            System.out.println(path);
            return;
        }

        if(root.left!=null) {
            print(root.left, path);
        }

        if(root.right!=null){
            print(root.right, path);
        }


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

        print(tree.root,"");

    }

}
