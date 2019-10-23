package com.leetcode2019.medium;

import java.util.*;

/*
https://leetcode.com/problems/valid-parentheses/
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */
public class RemoveInvalidParanthesis {
    public static List<String> removeInvalidParentheses(String s) {
        List<String> results = new ArrayList<>();
        if (s == null || s.length() == 0) {
            results.add("");
            return results;
        }
        Set<String> resultSet = new HashSet<>();
        int extraOpen = 0;
        int extraClose = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') extraOpen++;
            if (s.charAt(i) == ')') {
                if (extraOpen > 0) {
                    extraOpen--;
                } else {
                    extraClose++;
                }
            }
        }

        removeInvalid(s, 0, "", extraOpen, extraClose, resultSet);

        if (resultSet.size() == 0) {
            results.add("");
        } else {
            results.addAll(resultSet);
        }
        return results;
    }

    public static void removeInvalid(String s, int idx, String soFar, int extraOpen, int extraClose, Set<String> results) {
        if (idx > s.length() - 1) {
            if (extraOpen == 0 && extraClose == 0 && isValid(soFar)) {
                results.add(soFar);
            }
            return;
        }

        if (s.charAt(idx) == '(') {
            if (extraOpen > 0) {
                // this is equivalent to remove
                removeInvalid(s, idx + 1, soFar, extraOpen - 1, extraClose, results);
            }
            // backtrack ; dont remove
            removeInvalid(s, idx + 1, soFar + s.charAt(idx), extraOpen, extraClose, results);
        } else if (s.charAt(idx) == ')') {
            if (extraClose > 0) {
                // simulate remove
                removeInvalid(s, idx + 1, soFar, extraOpen, extraClose - 1, results);
            }
            // backtrack ; dont remove
            removeInvalid(s, idx + 1, soFar + s.charAt(idx), extraOpen, extraClose, results);
        } else {
            // normal character which means just move over and include
            removeInvalid(s, idx + 1, soFar + s.charAt(idx), extraOpen, extraClose, results);
        }
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(')');
            } else if (s.charAt(i) == ')' && (stack.isEmpty() || s.charAt(i) != stack.pop())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        removeInvalidParentheses(")(");
    }
}
