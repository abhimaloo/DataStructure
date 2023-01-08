package com.leetcode2022.top_facebook;

import java.util.Stack;

/*
Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "3+2*2"
Output: 7
Example 2:

Input: s = " 3/2 "
Output: 1
Example 3:

Input: s = " 3+5 / 2 "
Output: 5


Constraints:

1 <= s.length <= 3 * 105
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.
 */
public class BasicCalculatorII {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentNum = 0;
        char operator = '+';

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNum = (currentNum * 10) + (currentChar - '0');
            }
            // this condition works if character is an operator or its not an operator but was last element of the string
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == s.length() - 1) {
                // for negative operator: simply push the number with -ve sign on the stack
                if (operator == '-') {
                    stack.push(-1 * currentNum);
                } else if (operator == '+') {  // for positive operator do the same
                    stack.push(currentNum);
                } else if (operator == '*') {  // for multiplication - you need to actually evaluate the expression: means multiply what was on the stack top and current num and put it back on stack
                    stack.push(stack.pop() * currentNum);
                } else if (operator == '/') {  //for division - you need to actually evaluate the expression: means divide what was on the stack top and current num and put it back on stack
                    stack.push(stack.pop() / currentNum);
                }
                operator = currentChar;  // this is to set the operator at the last since we rely on older operator
                currentNum = 0;  // reset the number
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}
