package com.maloo.collections.concurrent.usage;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by abhimaloo on 9/21/14.
 */
public class CyclicBarrierRunnable implements Runnable
{
    CyclicBarrier barrier1 = null;
    CyclicBarrier barrier2 = null;

    public CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2){
        this.barrier1 = barrier1;
        this.barrier2 = barrier2;
    }

    @Override
    public void run() {

        System.out.println("Waiting At Barrier 1");
        try {
            Thread.sleep(1000);
            barrier1.await();
            System.out.println("waiting at barrier 2");
            Thread.sleep(1000);
            barrier2.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("Released");


    }


    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier1 = new CyclicBarrier(3,new Runnable() {
            @Override
            public void run() {
                System.out.println("Barrier1 executed : do something");
            }
        });

        CyclicBarrier barrier2 = new CyclicBarrier(2,new Runnable() {
            @Override
            public void run() {
                System.out.println("Barrier1 executed : do something");
            }
        });


        new Thread(new CyclicBarrierRunnable(barrier1,barrier2)).start();
        Thread.sleep(1000);
        new Thread(new CyclicBarrierRunnable(barrier1,barrier2)).start();



    }


}
