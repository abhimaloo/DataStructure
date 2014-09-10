package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 *
 * https://oj.leetcode.com/problems/4sum/
 * Created by abhimaloo on 9/9/14.
 */
public class FourSum {
    public static int[] input = {1, 0, -1, 0, -2, 2};
    public static int target = 0;

    public static void fourSum(int[] input, int target) {
        Arrays.sort(input);
        List<String> result = new ArrayList<>();
        HashSet<String> hashSet = new HashSet<>();

        for( int i=0; i< input.length; i++) {
            for( int j = i+1; j< input.length; j++) {
                int k = j+1;
                int l = input.length -1;
                while(k < l) {
                    int sum = input[i] + input[j] + input[k] + input[l];

                    if(sum < target) {
                        k ++;
                    } else if(sum  > target) {
                        l --;
                    } else {
                        StringBuilder s = new StringBuilder();
                        s.append(input[i]+","+ input[j]+ ","+ input[k] + ","+ input[l]);

                        if(!hashSet.contains(s.toString())) {
                            result.add(s.toString());
                        }
                        k++;
                        l--;
                    }
                }
            }
        }

        for(String s : result) {
            System.out.println("Result:" + s);
        }

    }


    public static void main(String[] args) {
        fourSum(input, target);
    }

}
