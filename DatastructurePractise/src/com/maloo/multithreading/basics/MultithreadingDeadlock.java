package com.maloo.multithreading.basics;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by abhimaloo on 8/2/14.
 */
public class MultithreadingDeadlock {

    private Account acc1 = new Account();
    private Account acc2 = new Account();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLock(Lock firstLock, Lock secondLock) throws InterruptedException {
        boolean firstLockAcquired = false;
        boolean secondLockAcquired = false;

        while(true){
            try {
                firstLockAcquired = firstLock.tryLock();
                secondLockAcquired = secondLock.tryLock();

            } finally {
                if(firstLockAcquired && secondLockAcquired){
                    return;
                }

                if(firstLockAcquired){
                    firstLock.unlock();
                }
                if(secondLockAcquired) {
                    secondLock.unlock();

                }
            }

            Thread.sleep(1000);
        }

    }

    public void first() throws InterruptedException {

        Random rand = new Random();
        for( int i =0; i< 10000; i++) {
            acquireLock(lock1,lock2);
            try {
                Account.transfer(acc1,acc2, rand.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }

        }
    }

    public void second() throws InterruptedException {

        Random rand = new Random();
        for( int i =0; i< 10000; i++) {
            acquireLock(lock2, lock1);

            try {
                Account.transfer(acc2,acc1, rand.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finish(){

        System.out.println("Account 1 balance :"+ acc1.getBalance() + " Account 2 balance :"+ acc2.getBalance());
        System.out.println("total balance :"+  (acc1.getBalance() + acc2.getBalance()));

    }

    public static void main(String[] args) throws InterruptedException {

        final MultithreadingDeadlock obj = new MultithreadingDeadlock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.first();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.second();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        obj.finish();

    }

}

class Account {
    public int amount = 10000;
    public void deposit(int amount) {
        this.amount += amount;
    }
    public void withdraw(int amount ) {
        this.amount -= amount;
    }

    public int getBalance(){
        return this.amount;
    }

    public static void transfer(Account account1, Account account2, int amount){
        account1.withdraw(amount);
        account2.deposit(amount);
    }
}
