package com.maloo.multithreading.interview;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by abhimaloo on 9/6/14.
 */
public class PractiseCountdownLatch implements Runnable{

    CountDownLatch latch ;

    public PractiseCountdownLatch(CountDownLatch latch) {
        this.latch = latch;
    }


    @Override
    public void run() {

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Running");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch  = new CountDownLatch(2);
        ExecutorService service = Executors.newFixedThreadPool(2);

        for( int i = 0; i< 20; i++) {
            service.submit(new PractiseCountdownLatch(latch));
        }

        service.shutdown();
        Thread.sleep(2000);
        latch.countDown();
        System.out.println("countedDown 1");
        Thread.sleep(2000);
        latch.countDown();
        System.out.println("counted Down 2");

        //System.out.println("Fininshed submitting tasks");
        service.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Done");

    }
}
