package com.datastructure2019.dp;

/*
https://leetcode.com/problems/paint-house-ii/
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5], [14,3,19]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.

*/
public class PaintHouseII {
    /*
    Solution - We are going to use DP table> there a re two independent variables in this - houses and colors

    DP Table

          W   R   B
      H1  17  2   17
      H2  18  33  7
      H3  21  10  37

      Out DP table will contain cross-section of Houses and colors. We are going to figure out the min cost for
      Only House1 - if painted in W/R/B color , the we find out min cost of House1, House2 if painted in W/R/B colors

      DP table will be filled from  DP[i,j] -> cost[i][j] + min(DP[i..j-1 ..j+1 ..n])
     */

    public static int minCostII(int[][] costs) {
        if (costs.length == 0) return 0;
        int n = costs[0].length;
        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < n; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (k != j && i - 1 >= 0) {
                        minCost = Math.min(minCost, costs[i - 1][k]);
                    }
                }
                costs[i][j] += minCost == Integer.MAX_VALUE ? 0 : minCost;
                if (i == costs.length - 1) {
                    minimum = Math.min(costs[i][j], minimum);
                }
            }
        }
        return minimum;
    }

    public static void main(String[] args) {
        int[][] costs = new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        System.out.println(minCostII(costs));
    }
}
