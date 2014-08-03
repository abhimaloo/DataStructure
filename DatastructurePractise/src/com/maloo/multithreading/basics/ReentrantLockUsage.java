package com.maloo.multithreading.basics;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by abhimaloo on 8/1/14.
 */
public class ReentrantLockUsage {

    public int count = 0;
    public Lock lock = new ReentrantLock();
    public void increment() {
        for( int i=0; i< 10000; i++){
            count++;
        }
    }
    public void first() {
        lock.lock();
        try {
            increment();
        }finally {
            lock.unlock();
        }


    }

    public void second() {
        lock.lock();
        try {
            increment();
        }finally {
            lock.unlock();
        }
    }

    public void finish(){
        System.out.println("Count is:"+ count);
    }

    public static void main(String[] args) throws InterruptedException {

        final ReentrantLockUsage obj = new ReentrantLockUsage();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.first();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

             obj.second();

            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        obj.finish();

    }
}
