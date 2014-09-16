package com.crackCode.stackAndQueue;

/**
 * Created by abhimaloo on 9/14/14.
 */
public class MyStack {
    int[] store = new int[10];
    int top = -1;


    public boolean isEmpty() {
        return top == -1;
    }
    public int peek() {
        if(top == -1) {
            return -1;
        }
        return store[top];
    }
    public void push(int data) {
        if(top == 9) {
            System.out.println("Overflow");
            return;
        } else {
            store[++top] = data;
        }
    }

    public int pop() {
      int response = -1;
      if(top == -1){
          System.out.println("Underflow");
      } else {
          response = store[top--];
      }

      return response;
    }


}
