package com.maloo.dynamicprogramming;

import com.maloo.trees.BST;

/**
 * This is a tricky application of binary search tree
 * Created by abhishekm787 on 7/8/14.
 */
public class OptimumBinarySearchTree {

    public static int [] input = {10, 12, 20};
    public static int [] frequency = {34, 8, 50};


    /**
     * Its a bit tricky ..
     * You have to calculate minCost[i][j] which contains the minCost
     * construction of binary tree for values ranging from index i to j inclusive
     *
     * NOTE: In this diagonals are filled out first ..
     *  and then double length chains  and so on..
     *
     */
    public static BST<Integer> binaryTree() {
        //it keeps the minCost of substring i to j inclusive
        int[][] minCost = new int[input.length][input.length];
        // it keeps the index of the root which will cost the minimum for creating a tree of substring between
        //i and j inclusive
        int[][] root = new int[input.length][input.length];


        //single length chains will have cost equals to their frequency
        for(int i=0; i<input.length; i ++) {
           minCost[i][i] = frequency[i];
           root[i][i] = i;
        }

        //Now handle double length and multiple length chains
        for(int l =2; l <= input.length; l++) {
            // start index runs from 0 to input.length - l +1
            //so that it only goes till it does not breaches the length boundary
            for (int i=0; i < input.length-l+1; i++){
                //find j which is the end column index
                int j = i+l-1;


                int min = Integer.MAX_VALUE;
                int pk = 0;
                //calculate sum of frequencies
                for( int k =i; k<=j; k++){
                    pk += frequency[k];
                }

                //iterate from i to j and try to give a chance to every index to become root
                // calculate the root which has the minimum cost

                for( int k =i; k<=j; k++){
                    if(min> (k>i?minCost[i][k-1]:0) + (k<j?minCost[k+1][j]:0) + pk){
                        min = (k>i?minCost[i][k-1]:0) + (k<j?minCost[k+1][j]:0) + pk;
                        root[i][j] = k;
                    }
                }
                //update the minimum cost to be the minimum among all the above k roots
                minCost[i][j] = min;
            }
        }

        System.out.println(" Minimum cost of BST is " +minCost[0][input.length-1]);
        /* Code to print the minCost grid .. use it for debugging
        for( int i = 0; i<input.length; i++){
            for( int j=0; j < input.length;j++) {
                System.out.print(" " + minCost[i][j] + " ") ;

            }
            System.out.println();
        } */

        //Now construct the binary tree
        BST<Integer> tree = new BST<>();
        construct(0, input.length-1, tree, root);
        /* code to print the tree returned .. for debugging
        tree.levelOrder(tree.root);
        */
        return tree;
    }

    /**
     * This method constructs a BST, deifned by the order of the nodes it root[][]
     * @param start
     * @param end
     * @param tree
     * @param root
     */
    public static void construct(int start, int end,BST<Integer> tree, int[][] root) {

        if(start>end){
            return ;
        } else {
            int rootIndex = root[start][end];
            tree.insert(input[rootIndex]);
            construct(start, rootIndex-1, tree, root);
            construct(rootIndex+1, end, tree, root);
        }

    }


    public static void main(String[] args) {
        binaryTree();
    }


}
