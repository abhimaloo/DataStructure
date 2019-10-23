package com.leetcode2019.easy;

import java.util.Stack;

/*
https://leetcode.com/problems/min-stack/
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.


Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

 */
public class MinStack {
    Stack<Integer> stack = new Stack<>();
    int min = Integer.MAX_VALUE;

    public MinStack() {

    }

    /*
    Trick is to push old min as well with newer min
    That way when you pop any current min, you can set the new min by popping one more time
     */
    public void push(int x) {
        if (x <= min) {
            min = x;
            stack.push(min);  // push the old min
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {  //if you have popped the current min
            min = stack.pop();   // pop one more time to reset the min to older min
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
