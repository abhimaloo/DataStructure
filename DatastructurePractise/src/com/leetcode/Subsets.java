package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by abhimaloo on 8/28/14.
 */
public class Subsets {
    public static int[] input = {1,2,3};


    public static void doSubset(int[] input, int index, List<Integer> subset) {
        if(index == input.length) {
            System.out.print("{");
            for(Integer s : subset) {
                System.out.print(s + ", ");
            }
            System.out.print("}");
            System.out.println();
            return ;
        }

        Arrays.sort(input);
        subset.add(input[index]);
        doSubset(input, index + 1, subset);
        subset.remove(subset.size() -1);
        doSubset(input, index +1, subset);

    }

    public static void main(String[] args) {
        doSubset(input, 0, new ArrayList<Integer>());
    }


}
