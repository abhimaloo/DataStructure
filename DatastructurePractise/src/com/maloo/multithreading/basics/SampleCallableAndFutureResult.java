package com.maloo.multithreading.basics;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by abhimaloo on 8/3/14.
 */
public class SampleCallableAndFutureResult {
    public static void main(String[] args) {

        ExecutorService exec = Executors.newFixedThreadPool(1);
        Future<Integer> result = exec.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random rand = new Random();
                int sleepTime = rand.nextInt(4000);
                if(sleepTime > 2000){
                    throw new IOException("Sleeping too  long");
                }
                Thread.sleep(sleepTime);
                return 1;
            }
        });

        exec.shutdown();
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getCause().toString()
            );
        }

    }
}
