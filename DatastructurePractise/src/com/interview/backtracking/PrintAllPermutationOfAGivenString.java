package com.interview.backtracking;

/**
 * Below are the permutations of string ABC.
 ABC, ACB, BAC, BCA, CAB, CBA

 http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 * Created by abhimaloo on 8/21/14.
 */
public class PrintAllPermutationOfAGivenString {

    public static String input = "ABC";

    /**
     * trick is to use backtracking algorithm
     * You will need to fix an element each time and for the rest of the string try swapping first element with all other elements and recurse
     * @param input
     * @param index
     */
    public static void printPermutation(char[] input, int index) {
        // base case when index will be equals to input length
        if(index == input.length) {
            // print the input string
            for( int i=0; i< input.length; i++) {
                System.out.print(input[i]);
            }
            System.out.println();
        } else {
            // here index contains the index from where the variable string starts.
            // any string before index is considered fixed
            // now try swapping index(first element of variable string) with all other variables
            for( int j = index; j < input.length; j++) {
                //swap index with j
                swap(index, j, input);
                // recurse by making this index as fixed and next substring as variable..
                printPermutation(input, index+1);
                // backtrack step  - swap again with j
                swap(index, j, input);
            }

        }

    }

    private static void swap(int i, int j, char[] input) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void main(String[] args) {
        printPermutation(input.toCharArray(), 0);
    }
}
