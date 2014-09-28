package com.facebook;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class PathSumII {

    public static void pathSum(BSTNode<Integer> root, int sum, List<Integer> path, List<List<Integer>> result, int targetSum) {
        if(root == null) {
            return ;
        }

        sum += root.data;
        path.add(root.data);

        if(root.left == null && root.right == null) {
            if(sum == targetSum) {
                result.add(new ArrayList<Integer>(path));
            }
            return ;
        }

        if(root.left!=null) {
            pathSum(root.left, sum, path, result, targetSum);
            path.remove(path.size()-1);
        }

        if(root.right != null) {
            pathSum(root.right, sum, path, result, targetSum);
            path.remove(path.size()-1);
        }
    }




    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,2);
        tree.insert(tree.root,6);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);

        List<List<Integer>> result = new ArrayList<>();

        pathSum(tree.root, 0,new ArrayList<Integer>(),result, 18);

        for(List<Integer> path : result) {
            for(int node : path) {
                System.out.print(" " + node + " ");
            }
            System.out.println();
        }

    }

}
