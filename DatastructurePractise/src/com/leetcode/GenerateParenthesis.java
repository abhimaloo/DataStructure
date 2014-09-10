package com.leetcode;



/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 "((()))", "(()())", "(())()", "()(())", "()()()"

 https://oj.leetcode.com/problems/generate-parentheses/
 * Created by abhimaloo on 9/9/14.
 */
public class GenerateParenthesis {

    /**
     * Trick is to use backtracking,  a little contrived though
     * pass two main arguments - remianingPairs and openCount
     * remainingPair means how many pairs of parenthesis are remaining to be generated
     * openCount means how many open parentesis '(' has been generated
     *
     * Now check if openCount > 0 ; add closing bracket'(' and recurse by setting openCount-1 , backtrack by removing the last added character
     *
     * Also check if remaining > 0 ; if yes, add an open parenthesis '(' to the result and recurse by setting remainingPair-1 and openCount +1
     *
     *
     * @param remainingpairs
     * @param openCount
     * @param inter
     */
    public static void generateParenthesis(int remainingpairs, int openCount, String inter) {
        // base condition .. if remaningPairs become 0 and open count becomes 0 as well
        if(remainingpairs == 0 && openCount ==0) {
            //print the string and return
            System.out.println(inter);
            return;
        }

        // if opencount is greater than 0
        if(openCount > 0) {
            // add closing bracket
            inter += ')';
            //recurse with openCount-1
            generateParenthesis(remainingpairs, openCount-1, inter);
            //backtrack
            inter = inter.substring(0, inter.length()-1);
        }

        //if remaining pairs is greater than 0
        if(remainingpairs > 0) {
            // add opening bracket
            inter += '(';
            //recurse with remainingPair -1, and openCount+1
            generateParenthesis(remainingpairs-1, openCount+1 , inter);
            //backtrack
            inter = inter.substring(0, inter.length()-1);
        }

    }

    public static void main(String[] args) {
        generateParenthesis(3, 0, "");
    }
}
