package com.leetcode2022.blind75;

import java.util.ArrayList;
import java.util.List;

public class OptimalAccountBalancing {

    public static int settleDebt(int[][] transactions) {
        // transaction structure is "from, to , amount"
        int[] settlement = new int[12];

        // this will make everything zero sum
        for (int i = 0; i < transactions.length; i++) {
            settlement[transactions[i][0]] += (transactions[i][2] * -1);
            settlement[transactions[i][1]] += transactions[i][2];
        }

        List<Integer> debtList = new ArrayList<>();
        for (int i = 0; i < settlement.length; i++) {
            if (settlement[i] != 0) {
                debtList.add(settlement[i]);
            }
        }

        return backtrack(debtList.toArray(new Integer[debtList.size()]), 0);
    }

    public static int backtrack(Integer[] settlement, int idx) {
        /*
        Idea is everything to the left of index has been settled hence move the index to the first non-zero(unsettled)
        debt .
         */
        while (idx < settlement.length && settlement[idx] == 0) {
            idx++;
        }

        // if you hae reached the end of array than return 0 (no trasaction needed)
        // base case
        if (idx == settlement.length) {
            return 0;
        }
        int minChecks = Integer.MAX_VALUE;
        // this is where you try to settle idx's debt with everyone on the right of it.
        for (int i = idx + 1; i < settlement.length; i++) {
            // this is to make sure that debt can be settled - should meet + and vice versa
            if (settlement[idx] * settlement[i] < 0) {
                settlement[i] += settlement[idx];  // settle the debt
                minChecks = Math.min(minChecks, backtrack(settlement, idx + 1) + 1);  // recurse for the next decision and add 1 to the settlement score
                settlement[i] -= settlement[idx];  // unrecord the decision; this is the backtracking step
            }
        }
        return minChecks;
    }

    public static void main(String[] args) {
        int[][] transactions = {{0, 1, 10}, {2, 0, 5}};
        int minChecks = settleDebt(transactions);
        System.out.println(minChecks);
    }
}
