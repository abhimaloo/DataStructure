package com.maloo.multithreading.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is a Thread safe implementation of SLOT interface, which has one single slot and have two operations get and put
 * Created by abhimaloo on 9/5/14.
 */
public class SlotImpl<T> implements Slot<T> {
    // this determines whether the slot is full or not
    public boolean full = false;
    // this is the backing store
    public T slot = null;
    // this lock synchronizes the access
    Lock lock = new ReentrantLock();
    // this condition checks if slot is empty
    Condition notEmpty = lock.newCondition();
    // this condition checks if slot is full
    Condition notFull = lock.newCondition();

    @Override
    public void put(T item) throws InterruptedException {

        try {
            //Step 1 : acquire the lock
            acquireLock(lock);
            //spinning lock .. in while loop check if slot is full.. and if its full ..put a notFull into wait situation
            while(full == true) {
                System.out.println("Waiting to Add ");
                // make all the not fulls wait because slot is full
                notFull.await();
            }
            // if you reached here it means slot became empty
            // put the item in the slot
            slot = item;
            // change full from false to true .. meaning slot is full again
            full = true;

            System.out.println("Added Item :"+ item.toString());
            // signal all notEmpty conditions that they can resume because slot os not empty anymore
            notEmpty.signalAll();
        } finally {
            // unlock the lock
            lock.unlock();
        }
    }

    @Override
    public T get() throws InterruptedException {
        T response = null;
        try {
            // Step 1 : axquire the lock
            acquireLock(lock);
            // spin lock , which keeps checking if the slot is empty it will keep all the notEmpty conditions to wait
            while(full == false) {
                System.out.println("Waiting for fetch");
                notEmpty.await();
            }
            // read the slot
            response = slot;
            // set the full to false
            full = false;
            // make the slot empty
            slot = null;

            System.out.println("Got Item :"+ response.toString());
            // signal all not fulls
            notFull.signalAll();
        } finally {
            // finally unlock the lock
            lock.unlock();
        }

        return  response;
    }

    // pretty standard process
    private void acquireLock(Lock lock) throws InterruptedException {

        // keep a boolean varibale to check whether we got the lock or not
        boolean lockAcquired = false;
        // in an infinite loop keep trying to acquire the lock
        while (true) {
            try {
                // try acquiring the lock
                lockAcquired = lock.tryLock();
            } finally {
                // if you got the lock exit this method
                if(lockAcquired) {
                    return;
                }
            }
            // sleep for some time since you did not got the lock .. will try again after 1 second
            Thread.sleep(1000);
        }

    }


    public static void main(String[] args) throws InterruptedException {
        final SlotImpl<Integer> slot = new SlotImpl<>();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // try putting 20 items one after another and sleep for 1 second in between
                for( int i = 0; i < 20 ; i++) {
                    try {
                        slot.put(i);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //try fetching all the 20 objects one after the another while sleeping for 1 second in between
                for( int i = 0; i < 20 ; i++) {
                    try {
                        slot.get();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }



}
