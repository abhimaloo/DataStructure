package com.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

 For example, given the following triangle
 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 Note:
 Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * Created by abhimaloo on 9/27/14.
 */
public class Triangle {

    public static List<List<Integer>> matrix = new ArrayList<>(Arrays.asList(Arrays.asList(2) ,
            Arrays.asList(3,4), Arrays.asList(6,5,7), Arrays.asList(4,1,8,3)));

    public static int minSum(int row, int index, int sum, int minSum, List<List<Integer>> matrix) {
        sum += matrix.get(row).get(index);
        if(row == matrix.size()-1) {
            if(minSum > sum) {
                return sum;
            }
            return minSum;
        }


        minSum = minSum(row+1,index, sum, minSum, matrix);

        if(index +1 < matrix.get(row+1).size()) {
            minSum = minSum(row+1, index+1, sum, minSum, matrix);
        }

        return minSum;
    }

    public static int minSumDP(List<List<Integer>> matrix) {

        int[][] minSum = new int[matrix.size()][matrix.get(matrix.size()-1).size()];

        for(int i = matrix.size()-1; i >= 0; i--) {
            for(int j = 0; j < matrix.get(i).size(); j++) {
                minSum[i][j] =  matrix.get(i).get(j);
                if(i+1 < matrix.size() && j+1<matrix.get(i+1).size()) {
                    minSum[i][j] += Math.min(minSum[i+1][j], minSum[i+1][j+1]);
                }
            }
        }

        return minSum[0][0];
    }


    public static void main(String[] args) {
        System.out.println(minSum(0,0,0,Integer.MAX_VALUE,matrix));
        System.out.println(minSumDP(matrix));


    }



}
