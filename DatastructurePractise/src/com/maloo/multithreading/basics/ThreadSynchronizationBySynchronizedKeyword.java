package com.maloo.multithreading.basics;

/**
 * Created by abhimaloo on 8/1/14.
 */
public class ThreadSynchronizationBySynchronizedKeyword {

    public int count = 0;

    public synchronized void increment(){
        count++;
    }

    public void doWork(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for( int i = 0; i<10000; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for( int i = 0; i<10000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        //make sure that both the threads finishes there job

        try {
            t1.join(); // this will make sure that main thread pauses till t1 joins
            t2.join(); // this will pause main thread till t2 joins
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(count);

    }

    public static void main(String[] args) {
        ThreadSynchronizationBySynchronizedKeyword th = new ThreadSynchronizationBySynchronizedKeyword();
        th.doWork();
    }

}
