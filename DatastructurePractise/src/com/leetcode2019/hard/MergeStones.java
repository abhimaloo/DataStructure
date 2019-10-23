package com.leetcode2019.hard;

/*
https://leetcode.com/problems/minimum-cost-to-merge-stones/
 */
public class MergeStones {
    // looks like a greedy solution
    /*
    Find k consecutive nodes which has minimum sum. Merge those and continue again
     */
    public static int mergeStones(int[] stones, int K) {
        int[] cost = new int[1];
        cost[0] = 0;
        mergeStones(stones, K, cost);
        return cost[0];
    }

    public static void mergeStones(int[] stones, int K, int[] cost) {
        if (stones.length == 1) return;
        if (stones.length < K) {
            cost[0] = -1;
            return;
        }

        int minleft = -1;
        int minright = -1;
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i <= stones.length - K; i++) {
            int sum = 0;
            for (int j = i; j < i + K; j++) {
                sum += stones[j];
            }
            if (sum < minCost) {
                minCost = sum;
                minleft = i;
                minright = i + K - 1;
            }
        }
        cost[0] = cost[0] + minCost;
        int[] newStones = new int[stones.length - K + 1];
        int newIdx = 0;
        for (int i = 0; i < stones.length; i++) {
            if (i == minleft) {
                newStones[newIdx++] = minCost;
                i = minright;
            } else {
                newStones[newIdx++] = stones[i];
            }
        }
        mergeStones(newStones, K, cost);
    }

    public static void main(String[] args) {
        System.out.println(mergeStones(new int[]{6, 4, 4, 6}, 2));
    }
}
