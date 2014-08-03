package com.maloo.multithreading.basics;

/**
 * Created by abhimaloo on 8/1/14.
 */
public class SampleThreadInterruptionUsingVolatileVariables implements Runnable{

    public volatile boolean running  = true;
    @Override
    public void run() {
        while(running) {
            for( int i=0; i<10; i++) {
                System.out.println(i);
            }
            try {
                Thread.sleep(100);
            }catch (InterruptedException ex){

            }

        }
    }
    public void shutdown(){
        running = false;
    }

    public static void main(String[] args) {
        SampleThreadInterruptionUsingVolatileVariables v1 = new SampleThreadInterruptionUsingVolatileVariables();
        Thread t1 = new Thread(v1);

        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        v1.shutdown();

    }
}
