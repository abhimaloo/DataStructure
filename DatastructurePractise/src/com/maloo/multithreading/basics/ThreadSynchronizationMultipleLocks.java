package com.maloo.multithreading.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by abhimaloo on 8/1/14.
 */
public class ThreadSynchronizationMultipleLocks {
    public Random random = new Random();
    public Object lock1 = new Object();
    public Object lock2 = new Object();
    public List<Integer> list1  = new ArrayList<>();
    public List<Integer> list2  = new ArrayList<>();

    public void stage1() {
        synchronized (lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(random.nextInt(100));
        }

    }

    public void stage2() {
        synchronized (lock2){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }

    }

    public void process() {
        for( int i =0; i< 1000; i++) {
            stage1();
            stage2();
        }
    }

    public void main() {

        System.out.println("Starting");
        long startTime = System.currentTimeMillis();

        Thread t1 = new  Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        Thread t2 = new  Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Total duration in ms :"+ String.valueOf(endTime - startTime));
        System.out.println("List 1 size :"+ list1.size()+ " List 2 size:"+ list2.size());
    }

    public static void main(String[] args) {
        ThreadSynchronizationMultipleLocks t = new ThreadSynchronizationMultipleLocks();
        t.main();
    }

}
