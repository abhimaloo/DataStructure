package com.leetcode2019.easy;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParanthesis {
    public static boolean isValidParanthesis(String s) {
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                st.push(')');
            } else if (c == '{') {
                st.push('}');
            } else if (c == '[') {
                st.push(']');
            } else {
                if (st.empty() || st.pop() != c) {
                    return false;
                }
            }
        }

        return st.isEmpty();
    }
}
