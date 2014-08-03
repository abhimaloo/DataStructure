package com.maloo.multithreading.basics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by abhimaloo on 8/1/14.
 */
public class ThreadPooling {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for( int i=0; i<5; i++){
            executor.submit(new Processor(i));
        }
        executor.shutdown();
        System.out.println("All tasks submitted");
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Processor implements Runnable{

    public int id;
    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Starting :"+ id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed:"+ id);
    }
}


