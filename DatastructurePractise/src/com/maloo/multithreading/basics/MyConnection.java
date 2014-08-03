package com.maloo.multithreading.basics;

import java.util.concurrent.Semaphore;

/**
 * Created by abhimaloo on 8/3/14.
 */
public class MyConnection {
    private static MyConnection instance = new MyConnection();
    private int connections = 0;
    Semaphore sem = new Semaphore(10);
    private void MyConnection() {
    }

    public static MyConnection getInstance(){
        return instance;
    }
    public void connect() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
            doConnect();
        } finally {
            sem.release();

        }
    }

    public void doConnect() {

        synchronized (this) {
            connections ++;
            System.out.println("Conncetion size : "+ connections );
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            connections --;
        }

    }


}

