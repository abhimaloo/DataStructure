package com.interview.trees;

/**
 * Created by abhimaloo on 8/8/14.
 */
public class PrintAllRootToLeafPaths<T extends Comparable<T>> extends BST<T> {


    /**
     * print all paths from root to node can only be printed like this..
     * append the node's data to recurring message string ..
     * if you find leaf node .. print it else recurse on the child which exist
     * @param root
     * @param path
     */
    public void printAllPaths(BSTNode<T> root, String path) {
        //EDGE case
       if(root == null){
           return;
       }

       //append data to the path
       path += " "+ root.data;

       // if leaf node print it
       if(root.left == null && root.right == null) {
           System.out.println(path);
       } else {
           //if left child exist .. recurse on left side
           if(root.left !=null ){
               printAllPaths(root.left, path);
           }
           // of right child exist recurse on right side
           if(root.right !=null) {
               printAllPaths(root.right, path);
           }
       }

    }


    public static void main(String[] args) {
        PrintAllRootToLeafPaths<Integer> tree = new PrintAllRootToLeafPaths<>();
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

        tree.printAllPaths(tree.root,"");
    }

}
