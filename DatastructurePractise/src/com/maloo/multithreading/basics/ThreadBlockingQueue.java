package com.maloo.multithreading.basics;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by abhimaloo on 8/1/14.
 */
public class ThreadBlockingQueue {
    public BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public void produce() throws InterruptedException {
        Random random = new Random();
        while(true){
            Integer item = random.nextInt(10);
            //System.out.println("Waiting for adding Item :"+ item);
            queue.put(item);
            //System.out.println("Item Added :"+ item);
        }

    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        while(true) {
            Thread.sleep(100);
            Integer randIndex = random.nextInt(10);
            if(randIndex ==0){
                Integer item = queue.take();
                System.out.println("Item consumed :"+ item+ ": Queue size "+ queue.size());
            }
        }
    }

    public static void main(String[] args) {
        final ThreadBlockingQueue bl = new ThreadBlockingQueue();
        ExecutorService producer = Executors.newFixedThreadPool(2);
        ExecutorService consumer = Executors.newFixedThreadPool(5);

        for(int i=0; i<2;i++){
            producer.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        bl.produce();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            consumer.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        bl.consume();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        producer.shutdown();
        consumer.shutdown();



    }


}
