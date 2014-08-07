package com.interview.arrays.dp;

/**
 * Given a sequence of matrices, find the most efficient way to multiply these matrices together.
 * Input - p[] = {40, 20, 30, 10, 30} where p(i) and p(i+1) are rows and columns of a matrix respectively
 * Output - 26000  (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30
 *
 * http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
 * Created by abhimaloo on 8/6/14.
 */
public class MatrixChainMultiplication {
    public static int [] p = {40, 20, 30, 10, 30};

    /**
     * trick is to use DP -
     * we need to calculate a cost[][] array which will keep minCost of multiplying i to j matrix
     * try finding out a Kth index which has the optimal result(separation by paranthesis)
     * between i and j.. try out every k and find the min cost among all the k's.
     * minCost[i][j] = minCost[i][k] + minCost[k+1][j] + (p(i-1) * p(j) * p(k))
     * @param p
     * @return
     */
    public static int findlowestMultiplicationOperations(int [] p) {
       int[][] cost = new int[p.length][p.length];
       // base case .. 0 length chains
       for( int i = 0; i< p.length;i++){
           cost[i][i] = 0;
       }

       //case for 2+ length chains
       for( int l=2;l<=p.length;l++) {
           for( int i=1; i<p.length-l+1; i++) {
               int j=i+l-1;
               cost[i][j] = Integer.MAX_VALUE;
               for( int k = i; k<j;k++){
                   int kCost = cost[i][k]+ cost[k+1][j]+ (p[i-1] * p[k] * p[j]);
                   if(kCost < cost[i][j]) {
                       cost[i][j]  = kCost;
                   }
               }

           }
       }


       return cost[1][p.length-1];

    }

    public static void main(String[] args) {
        System.out.println(findlowestMultiplicationOperations(p));
    }

}
