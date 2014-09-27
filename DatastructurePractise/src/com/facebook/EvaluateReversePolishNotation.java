package com.facebook;


import java.util.LinkedList;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

 Valid operators are +, -, *, /. Each operand may be an integer or another expression.

 Some examples:
 ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

 * Created by abhimaloo on 9/26/14.
 */
public class EvaluateReversePolishNotation {
    public static String[] input = {"4","13","5","/","+"};

    public static int evaluate(String[] input) {

        if(input == null || input.length == 0) {
            return 0;
        }

        LinkedList<Integer> stack  = new LinkedList();
        for(int i = 0; i< input.length; i++) {
            if(isInteger(input[i])) {
                stack.push(Integer.parseInt(input[i]));
            } else {
                if(stack.size() < 2) {
                    throw new RuntimeException("Incorrect Reverse Polish input");
                }

                int b = stack.pop();
                int a = stack.pop();

                switch(input[i]) {
                    case "+" :
                        stack.push(a + b);
                        break;
                    case "-" :
                        stack.push(a - b);
                        break;
                    case "*" :
                        stack.push(a * b);
                        break;
                    case "/" :
                        if(b == 0) {
                            throw new ArithmeticException("Divide BY Zero");
                        }
                        stack.push(a / b);
                        break;
                    default :
                        throw new RuntimeException("Incorrect Reverse Polish input");

                }

            }
        }

        return stack.pop();
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(evaluate(input));
    }


}
