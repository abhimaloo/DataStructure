package com.maloo.multithreading.basics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Countdown latch is used to synchronize all threads on a particular event
 * you can share a countdown latch between worker threads and ask them to do a job and count down the latch
 * finally latch.awaits() works as BIG join() method which will then resume the main method
 * Created by abhimaloo on 8/1/14.
 */
public class ThreadSynchronizationCountDownLatch {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for( int i=0; i<10;i++){
            threadPool.submit(new ProcessorLatch(latch));
        }
        try {
            latch.await();
            threadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed");
    }
}

class ProcessorLatch implements Runnable{

    public CountDownLatch latch;
   public  ProcessorLatch(CountDownLatch latch) {
       this.latch = latch;
   }
    @Override
    public void run() {
        System.out.println("Processing Started");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count :"+ latch.getCount());
        latch.countDown();

    }
}
