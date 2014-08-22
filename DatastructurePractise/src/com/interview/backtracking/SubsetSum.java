package com.interview.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * Subset sum problem is to find subset of elements that are selected from a given set whose sum adds up to a given number K.
 * We are considering the set contains non-negative values.
 * It is assumed that the input set is unique (no duplicates are presented).
 *
 * http://www.geeksforgeeks.org/backttracking-set-4-subset-sum/
 * Created by abhimaloo on 8/21/14.
 */
public class SubsetSum {
    public static int[] set = {10, 7, 5, 18, 12, 20, 15};
    public static int sum = 35;

    /**
     * Trick is to use backtracking ..
     * try each element and keep subtracting its value from sum. recursively go to the next element
     * backtrack by unrecording the element and remaining sum
     *
     */
    public static boolean findSubsetSum(int[] set, int remainingSum, int index, Set<Integer> subset) {
        // base case : exit condition
        if(remainingSum == 0) {
            // print all elements of the subset
            for(Integer element : subset) {
                System.out.print(" " + element + " ");
            }
            System.out.println();
            return true;
        } else {
            // if remaining sum is still positive
            if(remainingSum > 0) {
                // itearte over all the cjoices starting from index
                for( int i = index; i< set.length; i++) {

                    // record the move
                    subset.add(set[i]);
                    // updateb the remaining sum
                    remainingSum = remainingSum - set[i];
                    //recurse on next elements
                    if(findSubsetSum(set, remainingSum,index+1,subset)) {
                        return true;
                    }
                    //backtrack and unrecord the move
                    subset.remove(set[i]);
                    //reset the remaining sum back to the original one
                    remainingSum = remainingSum + set[i];

                }
            }
           return false;
        }
    }

    public static void main(String[] args) {
        findSubsetSum(set,sum,0,new HashSet<Integer>());
    }


}
