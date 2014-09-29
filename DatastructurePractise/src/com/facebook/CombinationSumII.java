package com.facebook;

import java.util.*;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class CombinationSumII {
    public static int[] input = {10,1,2,7,6,1,5};
    public static int target = 8;

    public static List<List<Integer>> combinationSum(int[] input, int target) {
        if(input == null || input.length == 0){
            return null;
        }

        Arrays.sort(input);
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(input, target, 0, new ArrayList<Integer>(), result, new HashSet<List<Integer>>());
        return result;
    }

    private static void combinationSum(int[] input, int target, int index, List<Integer> path, List<List<Integer>> result, Set<List<Integer>> dedup) {
        if(target < 0 || index >= input.length) {
            return;
        }

        if(target == 0){
            if(!dedup.contains(path)){
                result.add(new ArrayList<Integer>(path));
                dedup.add(path);
            }

            return;
        }

        path.add(input[index]);
        combinationSum(input, target - input[index], index+1, path, result, dedup);
        path.remove(path.size()-1);
        combinationSum(input, target, index+1, path, result, dedup);
    }

    public static void main(String[] args) {
        for(List<Integer> sum : combinationSum(input, target)) {
            for(int s : sum) {
                System.out.print(" "+ s + " ");
            }
            System.out.println();
        }
    }


}
