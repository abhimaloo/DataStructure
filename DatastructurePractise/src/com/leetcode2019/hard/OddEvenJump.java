package com.leetcode2019.hard;

import java.util.TreeMap;

public class OddEvenJump {
    //odd -> next bada
    // even -> next chota
    /*
    Trick is to understand the DP
    dp[i][j] = true means from index i if you can erach to target via j being even/odd
    DP formula would be
    dp[i][0] = dp[nextGreaterIndex][1]  -> since i is odd - the next step will become even hence [1]
    dp[i][1] = dp[nextlesserIndex][0] -> since i here is even the next step will be odd hence [0]

     */
    public static int oddEvenJumps(int[] A) {
        boolean[][] jump = new boolean[A.length][2];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(A[A.length - 1], A.length - 1);
        jump[A.length - 1][0] = true;
        jump[A.length - 1][1] = true;
        int success = 0;

        for (int i = A.length - 2; i >= 0; i--) {
            Integer nextGreater = treeMap.ceilingKey(A[i]);
            if (nextGreater != null) {
                jump[i][0] = jump[treeMap.get(nextGreater)][1];
            } else {
                jump[i][0] = false;
            }

            Integer nextSmaller = treeMap.floorKey(A[i]);
            if (nextSmaller != null) {
                jump[i][1] = jump[treeMap.get(nextSmaller)][0];
            } else {
                jump[i][1] = false;
            }
            treeMap.put(A[i], i);

            if (jump[i][0]) {
                success++;
            }
        }

        return success;

    }

    public static void main(String[] args) {
        System.out.println(oddEvenJumps(new int[]{10, 13, 12, 14, 15}));
    }
}
