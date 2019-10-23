package com.datastructure2019.dp;

/*
https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/
Given a sorted array keys[0.. n-1] of search keys and an array freq[0.. n-1] of frequency counts, where freq[i] is the number of searches to keys[i]. Construct a binary search tree of all keys such that the total cost of all the searches is as small as possible.
Let us first define the cost of a BST. The cost of a BST node is level of that node multiplied by its frequency. Level of root is 1.
 */
public class OptimalBinarySearchTree {
    /*
    The optimal substructure is
    for an interval (i,j) -> we try evey node as a root and calculate the cost.
    typical cost for Root at r is -  Sum(freq(i..j)) + Math.min(Cost(i,r-1) + Cost(r+1, j))
     */
    public static int minCost(int[] keys, int[] frequencies) {
        int[][] dp = new int[keys.length][keys.length];

        for (int i = 0; i < keys.length; i++) {
            dp[i][i] = frequencies[i];
        }

        for (int length = 2; length <= keys.length; length++) {
            for (int i = 0; i <= keys.length - length; i++) {

                int minCost = Integer.MAX_VALUE;
                int end = i + length - 1;
                int sum = getSum(frequencies, i, end);
                for (int j = i; j <= end; j++) {
                    if (j - 1 < i) {
                        minCost = Math.min(minCost, sum + dp[j + 1][end]);
                    } else if (j + 1 > end) {
                        minCost = Math.min(minCost, sum + dp[i][j - 1]);
                    } else {
                        minCost = Math.min(minCost, sum + dp[i][j - 1] + dp[j + 1][end]);
                    }
                }
                dp[i][end] = minCost;
            }
        }

        return dp[0][keys.length - 1];
    }

    private static int getSum(int freq[], int i, int j) {
        int sum = 0;
        for (int x = i; x <= j; x++) {
            sum += freq[x];
        }
        return sum;
    }

    public static void main(String[] args) {
        int keys[] = {10, 12, 20};
        int freq[] = {34, 8, 50};
        System.out.println(minCost(keys, freq));
    }

}
