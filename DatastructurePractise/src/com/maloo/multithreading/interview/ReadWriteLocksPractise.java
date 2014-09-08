package com.maloo.multithreading.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by abhimaloo on 9/7/14.
 */
public class ReadWriteLocksPractise implements Runnable{

    public int activeWriter = 0;
    public int waitingWriter = 0;
    public int activeReader = 0;
    public int waitingReader = 0;

    public Lock lock = new ReentrantLock();
    public Condition okToRead = lock.newCondition();
    public Condition okToWrite = lock.newCondition();


    public void acquireLock(Lock lock) {
        boolean lockAcquired = false;
        try {
            while (!lockAcquired) {
                lockAcquired = lock.tryLock();
            }
        }
        finally {
            if(lockAcquired) {
                return;
            }
        }

    }

    @Override
    public void run() {

    }

    public void read() throws InterruptedException {

        acquireLock(lock);

        while (activeWriter + waitingWriter > 0) {
            waitingReader ++;
            okToRead.await();
            waitingReader --;
        }
        activeReader ++;
        lock.unlock();

        System.out.println("Read Database "+ Thread.currentThread().getId());

        acquireLock(lock);
        activeReader --;
        if(activeReader ==0 && waitingWriter >0) {
            okToWrite.signal();
        }

        lock.unlock();

    }

    public void write() throws InterruptedException {

        acquireLock(lock);
        while (activeWriter + activeReader > 0) {
            waitingReader ++;
            okToWrite.await();
            waitingReader --;
        }

        activeReader ++;
        lock.unlock();

        System.out.println("Writing to the Database" + Thread.currentThread().getId());

        acquireLock(lock);
        activeWriter --;
        if(activeWriter > 0) {
            okToWrite.signal();
        } else if(waitingReader > 0) {
            okToRead.signalAll();
        }

        lock.unlock();


    }


}
