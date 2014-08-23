package com.leetcode;

import java.util.LinkedList;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * https://oj.leetcode.com/problems/evaluate-reverse-polish-notation/
 * Created by abhimaloo on 8/22/14.
 */
public class EvaluateReversePolish {
    public static String[] expr = {"2","1","+","3","*"};

    /**
     * Idea is to use the stack. iterate over the incoming string array and check is the symbol is an operator or not.
     * If its not an operator push it to the stack
     * else pop top 2 item of the stack and apply the operator in the symbol to it. push the result back to the stack.
     * @param expr
     * @return
     */
    public static int evaluate(String [] expr) {
        LinkedList<String> stack = new LinkedList<>();
        String operators = "+-*/";
        for(String symbol : expr ) {
           if(!operators.contains(symbol)) {
               stack.push(symbol);
           } else {
               if(stack.size() >=2) {
                  int operand1 = Integer.parseInt(stack.pop());
                  int operand2 = Integer.parseInt(stack.pop());
                  int operatorIndex = operators.indexOf(symbol);
                   switch(operatorIndex) {
                      case 0 : stack.push(String.valueOf(operand1 + operand2));
                          break;
                      case 1 : stack.push(String.valueOf(operand1 - operand2));
                           break;
                       case 2 : stack.push(String.valueOf(operand1 * operand2));
                           break;
                       case 3 : stack.push(String.valueOf(operand1 / operand2));
                           break;
                      default: break;
                  }
               }
           }
        }

        return stack.size() == 1 ? Integer.parseInt(stack.pop()): -1;
    }

    public static void main(String[] args) {
        System.out.println(evaluate(expr));
    }
}
