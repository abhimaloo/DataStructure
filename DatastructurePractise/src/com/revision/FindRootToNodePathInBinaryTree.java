package com.revision;

import com.interview.trees.DistanceBetweenTwoNodes;
import com.interview.trees.impl.BSTNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhimaloo on 9/20/14.
 */
public class FindRootToNodePathInBinaryTree {

    public static boolean find(BSTNode<Integer> root, int value, List<Integer> path) {
        if(root == null){
            return false;
        }

        path.add(root.data);

        if(root.data.equals(value)) {
            return true;
        }

        if(find(root.left, value, path) || find(root.right, value, path)){
            return true;
        }
        //backtrack
        path.remove(path.size()-1);
        return false;

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

        List<Integer> path = new ArrayList<>();
        System.out.println(find(tree.root, 14, path));
        for(int i: path){
            System.out.println(i);
        }
    }
}
