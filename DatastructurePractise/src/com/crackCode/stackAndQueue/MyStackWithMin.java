package com.crackCode.stackAndQueue;

/**
 * Created by abhimaloo on 9/14/14.
 */
public class MyStackWithMin extends MyStack {
    MyStack min = new MyStack();

    public void minPush(int data) {
        if(top == 9) {
            System.out.println("Overflow");
            return;
        } else {
            store[++top] = data;
            if(min.peek() == -1 || data <= min.peek()) {
                min.push(data);
            }
        }
    }

    public int minPop() {

        int response = -1;
        if(top == -1){
            System.out.println("Underflow");
        } else {
            response = store[top--];
            if(response == min.peek()){
                min.pop();
            }
        }

        return response;
    }

    public int min() {

        if(min.top == -1) {
            return Integer.MAX_VALUE;
        } else {
            return min.peek();
        }
    }

    public static void main(String[] args) {
        MyStackWithMin s = new MyStackWithMin();
        s.minPush(6);
        s.minPush(8);
        s.minPush(2);
        s.minPush(3);
        s.minPush(1);
        s.minPush(7);

        System.out.println(s.min());
        s.minPop();
        s.minPop();
        s.minPop();
        s.minPop();
        System.out.println(s.min());

    }
}
