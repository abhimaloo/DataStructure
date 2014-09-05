package com.maloo.multithreading.interview;

/**
 * Created by abhimaloo on 9/4/14.
 */
public class InterruptThread implements Runnable{

    @Override
    public void run() {

            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("Working");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                     Thread.currentThread().interrupt();
                }

                System.out.println("Some more work");
            }




    }


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new InterruptThread());
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
