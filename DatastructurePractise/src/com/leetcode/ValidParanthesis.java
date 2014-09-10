package com.leetcode;


import java.util.LinkedList;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

 https://oj.leetcode.com/problems/valid-parentheses/
 * Created by abhimaloo on 9/9/14.
 */
public class ValidParanthesis {

    public static String input = "()[]{}{[]}";


    /**
     * Trick is to use Stack like expression evaluation
     * find the peek and put it in switch statement, if its the opening one and array contains the closing one, pop the stack
     * else push the character on the stack..
     *
     * finally check if stack is empty or not
     * @param input
     * @return
     */
    public static boolean isValid(char[] input) {

        LinkedList<Character> stack = new LinkedList<>();

        for(int i=0; i< input.length; i++) {
            if(stack.peek() != null) {
                // get the top element
                char c = stack.peek();
                //switch the top element
                switch(c) {
                    //if the top element is opening bracket of any type
                    case '(':
                        //check if current character on input array is closing bracket or not
                        if(input[i] == ')') {
                            //pop the stack
                            stack.pop();
                        } else {
                            //push the element
                            stack.push(input[i]) ;
                        }
                        break;
                    case '[' :
                        if(input[i] == ']') {
                            stack.pop();
                        } else {
                            stack.push(input[i]) ;
                        }
                        break;
                    case '{' :
                        if(input[i] == '}') {
                            stack.pop();
                        } else {
                            stack.push(input[i]) ;
                        }
                        break;
                    //if any other character like closing '}' is on stack top, push it to the stack
                    default:
                        stack.push(input[i]) ;
                        break;
                }
            } else {
                stack.push(input[i]);
            }
        }

        return stack.isEmpty();

    }


    public static void main(String[] args) {
        System.out.println(isValid(input.toCharArray()));
    }
}
