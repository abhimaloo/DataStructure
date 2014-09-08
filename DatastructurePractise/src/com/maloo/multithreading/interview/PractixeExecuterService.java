package com.maloo.multithreading.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by abhimaloo on 9/6/14.
 */
public class PractixeExecuterService {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service  = Executors.newFixedThreadPool(5);

        for( int i = 0; i< 20; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getId());
                }
            });
        }

        service.shutdown();

        System.out.println("All tasks submitted");
        service.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Everything finished");
    }
}
