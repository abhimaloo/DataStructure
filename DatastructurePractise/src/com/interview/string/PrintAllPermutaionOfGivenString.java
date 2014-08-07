package com.interview.string;

/**
 * A permutation, also called an “arrangement number” or “order,” is a rearrangement of the elements of
 * an ordered list S into a one-to-one correspondence with S itself. A string of length n has n! permutation.
 *
 * Below are the permutations of string ABC.
 * ABC, ACB, BAC, BCA, CAB, CBA
 *
 * http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 * Created by abhimaloo on 8/6/14.
 */
public class PrintAllPermutaionOfGivenString {
    public static String input = "ABCD";

    /**
     * trick is to use back tracking
     * keep a window of fixed elements ..keep swapping non fixed elements with each other and recurse
     * @param input
     * @param start
     * @param end
     */
    public static void printAllPermutation(char[] input, int start, int end) {
        // if start reaches end ..that means we cannot recurse anymore hence print the input
        if(start == end){
            for( int p =0;  p < input.length; p++){
                System.out.print(input[p]);
            }
            System.out.println();
        } else {
            int j=0;
            // iterate on  all the nonfixed following ones
            for(j=start; j<input.length; j++) {
                //swap the start with one of the nonFixed element
                swap(input,start,j);
                //recurse by making start fixed ..
                printAllPermutation(input,start+1,end);
                //backtracking by swap of start and non fixed again
                swap(input,start,j);
            }
        }

    }

    private static void swap(char[] input, int start, int j) {
        char temp = input[start];
        input[start] = input[j];
        input[j] = temp;
    }

    public static void main(String[] args) {
        printAllPermutation(input.toCharArray(), 0, input.length()-1);
    }
}
