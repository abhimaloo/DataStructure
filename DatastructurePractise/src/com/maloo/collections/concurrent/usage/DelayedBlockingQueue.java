package com.maloo.collections.concurrent.usage;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by abhimaloo on 9/21/14.
 */
public class DelayedBlockingQueue {

    public static void main(String[] args) throws InterruptedException {

         class Node implements Delayed {

             public int data;

             public Node(int data) {
                 this.data = data;
             }

             @Override
             public long getDelay(TimeUnit unit) {
                 return unit.toSeconds(10);
             }

             @Override
             public int compareTo(Delayed o) {
                 return 0;
             }
         }
        DelayQueue<Node> queue = new DelayQueue<>();
        for( int i = 0; i< 20; i++) {
            queue.put(new Node(i));
        }

        for( int i = 0; i< 20; i++){
            System.out.println(queue.take().data);
        }

    }
}
