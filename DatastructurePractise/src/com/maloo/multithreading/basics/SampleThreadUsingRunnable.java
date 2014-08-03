package com.maloo.multithreading.basics;

/**
 * Created by abhimaloo on 8/1/14.
 */
public class SampleThreadUsingRunnable implements Runnable {

    @Override
    public void run() {
        while(true) {
            for( int i=0; i<10; i++) {
                System.out.println(i);
            }
            try {
                Thread.sleep(100);
            }catch (InterruptedException ex){

            }

        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new SampleThreadUsingRunnable());
        t1.start();
    }


}
