package com.maloo.multithreading.basics;

import java.util.Random;

/**
 * Created by abhimaloo on 8/3/14.
 */
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
              for( int i=0; i< 1E8; i++)
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted !!");
                    break;
                }
                Math.sin(new Random().nextDouble());
            }
        });

        t1.start();

        Thread.sleep(1);
        t1.interrupt();
        t1.join();



        System.out.println("Finishing");
    }
}
