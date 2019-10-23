package com.concurrency2019;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue {
    private Queue<Integer> store = new LinkedList<>();
    private final int maxSize;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();


    public BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void offer(int input) {
        lock.lock();
        try {
            while (store.size() == maxSize) {
                notFull.await();
            }
            System.out.println("Adding: " + input);
            store.offer(input);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int take() {
        int ret = -1;
        lock.lock();
        try {
            while (store.size() == 0) {
                notEmpty.await();
            }
            ret = store.poll();
            System.out.println("Reading: " + ret);
            notFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return ret;
    }
}
