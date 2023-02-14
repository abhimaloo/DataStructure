package com.leetcode2022.async;

public class SimpleThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello World" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new SimpleThread());
        thread.start();
        System.out.println(Thread.currentThread().getName());

    }
}
