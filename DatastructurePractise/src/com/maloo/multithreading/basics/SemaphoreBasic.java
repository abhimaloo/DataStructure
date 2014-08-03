package com.maloo.multithreading.basics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by abhimaloo on 8/3/14.
 */
public class SemaphoreBasic {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0; i< 200; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    MyConnection.getInstance().connect();
                }
            });
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
    }
}



