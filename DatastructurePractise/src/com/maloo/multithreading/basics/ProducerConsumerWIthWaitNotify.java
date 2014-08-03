package com.maloo.multithreading.basics;

import java.util.Scanner;

/**
 * Created by abhimaloo on 8/1/14.
 */
public class ProducerConsumerWIthWaitNotify {

    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Producer Thread Running");
            wait();
            System.out.println("Resumed");
        }
    }

    public void consumer() throws InterruptedException {
        Scanner scan  = new Scanner(System.in);
        Thread.sleep(5000);
        synchronized (this) {
            System.out.println("Awaiting for Return Key");
            scan.nextLine();
            System.out.println("Return Key pressed");
            notify();
            Thread.sleep(5000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ProducerConsumerWIthWaitNotify obj = new ProducerConsumerWIthWaitNotify();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();



    }
}
