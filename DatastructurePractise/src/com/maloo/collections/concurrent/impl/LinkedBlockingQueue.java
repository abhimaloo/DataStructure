package com.maloo.collections.concurrent.impl;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * VEry educational data structure implementation
 * Created by abhimaloo on 9/21/14.
 */
public class LinkedBlockingQueue<T> {
    // using a linked list to hold the items
    public LinkedList<T> queue = new LinkedList();

    // this keeps the count of elements present in the queue
    public volatile AtomicInteger count = new AtomicInteger(0);

    // its the upper bound
    public volatile int capacity = 10;

    //this is the lock for all the producers. Only one producer can put an item on the queue at a time
    private volatile Lock putLock  = new ReentrantLock();
    // this condition is the waiting condition for the producers if queue is full
    private volatile Condition notFull = putLock.newCondition();

    // this is the lock for all the consumers.. only one consumer at a time will be able to add stuff to queue
    private volatile Lock takeLock  = new ReentrantLock();
    // this condition is waiting condiiton for consumers if queue is empty
    private volatile Condition notEmpty = takeLock.newCondition();


    /**
     * Implementation includes locking the putlock , and waiting if queue is full
     *
     * @param data
     * @throws InterruptedException
     */
    public void put(T data) throws InterruptedException{

        int oldCount = -1;
        putLock.lock();
        try {
            while(count.get() == capacity) {
                notFull.await();
            }

            queue.addLast(data);

            // very tricky logic .. It returns  the old value
            oldCount = count.getAndIncrement();
            // here you awake other sleeping producer to awake him since there is one more empty slot
            if(oldCount + 1 < capacity) {
                notFull.signal();
            }

        } finally {
            putLock.unlock();
        }

        // if old value of count was zero .. that means some of the consumers may be waiting for the item .. signal one of them
        if(oldCount == 0) {
            takeLock.lock();
            try {
                notEmpty.signal();
            } finally {
                takeLock.unlock();
            }
        }

    }


    public T take() throws InterruptedException{
        T data  = null;
        int oldCount = -1;
        takeLock.lock();
        try {
            while(count.get() == 0) {
                notEmpty.await();
            }

            data = queue.removeFirst();

            // it returns old value of count
            oldCount = count.getAndDecrement();
            // if old value -1 is greater than value .. means there are few more avaialble items to be consumed .. hence wake up another consumer
            if(oldCount -1 > 0) {
                notEmpty.signal();
            }

        } finally {
            takeLock.unlock();
        }

        // if old value of blocking queue was at capacity, which means there could be some producers waiting to add more elements
        // so signal one of the producer
        if(oldCount == capacity) {
            putLock.lock();
            try {
                notFull.signal();
            } finally {
                putLock.unlock();
            }
        }

        return data;
    }


    public static void main(String[] args) throws InterruptedException {
        final LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        List<Thread> consumers = new ArrayList<>();
        List<Thread> producers = new ArrayList<>();

        // start 20 consumers
        for(int i =0; i< 20; i++) {
            consumers.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println();
                    try {
                        System.out.println("Thread :" +Thread.currentThread().getName()+ "Took : " + queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }));

            producers.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println();
                    try {
                        System.out.println("Thread "+Thread.currentThread().getName()+ "Put : " +Thread.currentThread().getName());
                        queue.put(Integer.parseInt(Thread.currentThread().getName()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }));

        }

        // start all the 20 consumers
        for( int i = 0; i< 20; i++) {
            consumers.get(i).setName(String.valueOf(i));
            consumers.get(i).start();
        }

       Thread.sleep(5000);

        // start all the 20 producers
        for( int i = 0; i< 20; i++) {
            producers.get(i).setName(String.valueOf(i));
            producers.get(i).start();
        }


        // wait till they all join
        for( int i = 0; i< 20; i++) {
           producers.get(i).join();
           consumers.get(i).join();
        }

    }






}
