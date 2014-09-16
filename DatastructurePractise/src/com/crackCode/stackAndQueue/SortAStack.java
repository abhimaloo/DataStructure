package com.crackCode.stackAndQueue;

/**
 * Write a program to sort a stack in ascending order. You should not make any assumptions about how the stack is implemented.
 * The following are the only functions that should be used to write this program: push | pop | peek | isEmpty.
 * Created by abhimaloo on 9/14/14.
 */
public class SortAStack {

    /*
    Idea is to use an additional stack where you keep elements in increasing order
     */
    public static MyStack sort(MyStack s) {
        MyStack r = new MyStack();

        // iterate through all the elements of stack s
        while(!s.isEmpty()) {
            // pop one element at a time
            int data = s.pop();
            // check if r's peek is greater than data or not
            while(!r.isEmpty() && r.peek() > data) {
                // make sure that r's peek contains something less than data
                s.push(r.pop());
            }
            // push the data on r
            r.push(data);
        }

        //return r
        return r;
    }

    public static void main(String[] args) {
        MyStack r = new MyStack();
        r.push(4);
        r.push(11);
        r.push(5);
        r.push(7);
        r.push(9);
        MyStack s = sort(r);




    }

}
