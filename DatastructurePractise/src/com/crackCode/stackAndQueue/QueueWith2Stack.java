package com.crackCode.stackAndQueue;

/**
 * Created by abhimaloo on 9/14/14.
 */
public class QueueWith2Stack {
    MyStack s1 = new MyStack();
    MyStack s2 = new MyStack();

    public void enqueue(int data) {
        s1.push(data);
    }

    public int dequeue() {
        if(s2.isEmpty()) {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        return s2.pop();
    }

    public static void main(String[] args) {
        QueueWith2Stack queue = new QueueWith2Stack();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue.dequeue());
        queue.enqueue(5);
        queue.enqueue(6);
        System.out.println(queue.dequeue());

    }

}
