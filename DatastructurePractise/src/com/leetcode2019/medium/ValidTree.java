package com.leetcode2019.medium;

/*
https://leetcode.com/problems/graph-valid-tree/
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 */
public class ValidTree {
    /*
    Few things to check for a valid tree -
    a) Number of Edges should less than n-1
    b) Single Root
    c) no cycles
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0 || edges.length == 0) return false;

        if (edges.length != n - 1) return false;

        // Apply Union Find Algorithm
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }


        for (int i = 0; i < edges.length; i++) {
            int left = find(parent, edges[i][0]);
            int right = find(parent, edges[i][1]);
            if (left == right) {
                return false;
            } else {
                // Union them
                parent[left] = right;
            }
        }

        return true;
    }

    public int find(int[] parent, int num) {
        if (parent[num] == -1) {
            return num;
        }

        return find(parent, parent[num]);
    }
}
