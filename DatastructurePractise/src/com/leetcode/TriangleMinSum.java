package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by abhimaloo on 9/21/14.
 */
public class TriangleMinSum {


    public static int minSum(List<List<Integer>> triangle, int row, int column, int sum, int minSum) {
       sum += triangle.get(row).get(column);

       if(row == triangle.size() -1) {
           return Math.min(sum, minSum);
       }

       minSum = minSum(triangle, row+1, column, sum, minSum);
       minSum = minSum(triangle, row+1, column+1, sum, minSum);
       return minSum;

    }



    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
        System.out.println(minSum(triangle, 0, 0, 0, Integer.MAX_VALUE));


    }
}
