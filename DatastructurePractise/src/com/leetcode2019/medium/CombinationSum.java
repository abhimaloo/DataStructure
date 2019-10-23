package com.leetcode2019.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum {
    /*
    Solution is to think like knapsack problem -
    for target value t - the combinations to reach target t = combination(combination[t-a[i]])
    It means - for eg - in array {2,3,6,7} and target = 7
    total combinations to reach to target 7 would be -
    for every element of array a[i] (ex - 2) in this case
    target-a[i] = 5, find all the combinations to reach target 5 and then add 2 to it and call it a new combination.

    THIS IS TOP DOWN DP APPROACH

     */
    public static List<List<Integer>> combinationSum(int[] cands, int t) {
        Arrays.sort(cands); // sort candidates to try them in asc order
        // thats where we are
        List<List<List<Integer>>> dp = new ArrayList(t + 1);
        dp.add(0, new ArrayList<>());

        // iterate over all the possible Ts
        for (int i = 1; i <= t; i++) {
            List<List<Integer>> res = new ArrayList<>();
            // iterate over all the candidates which are less than target
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                // you found a candidate eqauls to target
                if (cands[j] == i) {
                    res.add(Arrays.asList(cands[j]));
                } else {
                    // if not candidate is not equals
                    for (List<Integer> combinations : dp.get(i - cands[j] - 1)) {
                        // this is dedup logic ; since we are keeping things sorted
                        if (cands[j] <= combinations.get(0)) {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(cands[j]);
                            temp.addAll(combinations);
                            res.add(temp);
                        }
                    }
                }

            }
            dp.add(i - 1, res);
        }

        return dp.get(t - 1);
    }

    public static void main(String[] args) {
        List<List<Integer>> res = combinationSum(new int[]{2, 3, 6, 7}, 7);

    }

}
