package com.leetcode2019.medium;

public class UniqueBinarySearchTrees {
    public static int uniqueBinarySearchTree(int n) {
        int[] g = new int[n + 1];
        g[0] = 1;
        g[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                g[i] += g[j - 1] * g[i - j];
            }
        }

        return g[n];
    }

    public static void main(String[] args) {
        System.out.println(uniqueBinarySearchTree(3));
    }
}
