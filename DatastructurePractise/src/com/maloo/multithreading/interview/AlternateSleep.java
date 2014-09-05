package com.maloo.multithreading.interview;

/**
 * Created by abhimaloo on 9/4/14.
 */
public class AlternateSleep implements Runnable{

    public volatile boolean isSleeping = false;



    @Override
    public void run() {
        while(true) {
            System.out.println("Doing some work");

            try {
                isSleeping = true;
                Thread.sleep(10000);
                isSleeping = false;
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted while doing some work");
                    return;
                }

            } catch (InterruptedException e) {
                System.out.println("Interrupted while sleeping");
                return;
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

       AlternateSleep obj =  new AlternateSleep();
       Thread t = new Thread(obj);
       t.start();

       Thread.sleep(2000);
       while(true) {
           if(!obj.isSleeping) {
               t.interrupt();
               break;
           }

       }




    }
}
