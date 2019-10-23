package com.leetcode2019.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EvaluateReversePolishNotation {
    public static int evalRPN(String[] tokens) {
        Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        int result = 0;

        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (!operators.contains(s)) {
                stack.push(Integer.parseInt(s));
            } else {
                int operand1 = stack.pop();
                int operand2 = stack.pop();
                switch (s) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "-":
                        stack.push(operand2 - operand1);
                        break;
                    case "*":
                        stack.push(operand2 * operand1);
                        break;
                    case "/":
                        stack.push(operand1 == 0 ? 0 : operand2 / operand1);
                        break;
                }
            }
        }
        result = stack.pop();
        return result;
    }

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"1", "2", "+", "3", "*"}));

    }
}
