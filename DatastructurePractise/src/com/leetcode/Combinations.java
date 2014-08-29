package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

 For example,
 If n = 4 and k = 2, a solution is:

 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]

 https://oj.leetcode.com/problems/combinations/

 * Created by abhimaloo on 8/28/14.
 */
public class Combinations {

    /**
     * trick is to use DFS or backtracking ..
     * keep 2 rotating variables start and currentNumber
     * break the recursion when currentNumber becomes k .. add the temporary result to end result
     *
     * else iterate i from start to n ..
     * add i into temporary result
     * recurse on 1 by adding one to start and currentNumber
     * backtrack by removing the i from temporary result
     *
     *
     * @param start
     * @param currentNumber
     * @param n
     * @param k
     * @param temp
     * @param result
     */

    public static void combination(int start, int currentNumber, int n, int k, List<Integer> temp, List<List<Integer>> result ) {
        if(currentNumber == k) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        for( int i = start; i < n; i ++) {
            temp.add(i);
            // recurse with next position
            combination(start+1,currentNumber+1, n, k , temp, result);
            //backtrack
            temp.remove(temp.size() -1);
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        combination(1, 0, 5, 2, new ArrayList<Integer>(), result );

        for(List<Integer> list : result) {
            for(Integer i : list) {
                System.out.print(" " + i + " ");
            }
            System.out.println();
        }
    }
}
