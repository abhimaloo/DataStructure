package com.maloo.multithreading.interview;

/**
 * Created by abhimaloo on 9/4/14.
 */
public interface Slot<T> {
    public T get() throws InterruptedException;
    public void put(T item) throws InterruptedException;
}
