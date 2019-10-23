package com.datastructure2019.dp;

import java.util.Stack;

/*
https://leetcode.com/problems/longest-valid-parentheses/
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
Accepted
210,742
Submissions
805,210
 */
public class LongestValidParanthesis {
    /*
      Trick is to scan left to right
      check if current character is ')' and there is a '(' referred by the top of stack index then pop the stack and find the maximum between i - stack.peek (newer top)
      other wise just push the index on the stack
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> indexStack = new Stack<>();
        int max = 0;
        indexStack.push(-1); // this is very important

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && indexStack.peek() != -1 && s.charAt(indexStack.peek()) == '(') {
                indexStack.pop();
                max = Math.max(i - indexStack.peek(), max);
            } else {
                indexStack.push(i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())()()("));
    }
}
