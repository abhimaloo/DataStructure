package com.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class CombinationSum {
    public static int[] input = {2,3,6,7};
    public static int target = 7;

    public static List<List<Integer>> combinationSum(int [] input, int target) {
        if(input == null || input.length == 0){
            return null;
        }

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(input);
        recursiveSumFind(input, target, 0, new ArrayList<Integer>(), result);
        return result;

    }

    private static void recursiveSumFind(int[] input, int targetSum, int index, ArrayList<Integer> path, List<List<Integer>> result) {
        if(input == null) {
            return;
        }

        if(targetSum < 0) {
            return;
        }

        if(targetSum == 0) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for( int i = index; i< input.length; i++) {
            if(input[i] > targetSum) {
                break;
            }

            path.add(input[i]);
            recursiveSumFind(input, targetSum - input[i], i, path, result);
            //backtrack
            path.remove(path.size()-1);
        }

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
