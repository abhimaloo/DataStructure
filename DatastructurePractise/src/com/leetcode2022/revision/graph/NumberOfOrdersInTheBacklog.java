package com.leetcode2022.revision.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/number-of-orders-in-the-backlog/
 */
public class NumberOfOrdersInTheBacklog {
    public int getNumberOfBacklogOrders(int[][] orders) {
        int buyType = 0;
        PriorityQueue<Order> buyBacklog = new PriorityQueue<>((o1, o2) -> o2.price - o1.price); // descending order
        PriorityQueue<Order> sellBacklog = new PriorityQueue<>(Comparator.comparingInt(o -> o.price)); //ascending order

        for (int[] order : orders) {
            if (order[2] == buyType) {
                buyBacklog.offer(new Order(order[0], order[1]));
            } else {
                sellBacklog.offer(new Order(order[0], order[1]));
            }

            while (!buyBacklog.isEmpty() && !sellBacklog.isEmpty() && buyBacklog.peek().price >= sellBacklog.peek().price) {
                int minSettle = Math.min(buyBacklog.peek().quantity, sellBacklog.peek().quantity);
                buyBacklog.peek().setQuantity(buyBacklog.peek().quantity - minSettle);
                sellBacklog.peek().setQuantity(sellBacklog.peek().quantity - minSettle);

                if (buyBacklog.peek().quantity == 0) {
                    buyBacklog.poll();
                }

                if (sellBacklog.peek().quantity == 0) {
                    sellBacklog.poll();
                }
            }
        }

        int res = 0, mod = 1000000007;
        for (Order o : sellBacklog) {
            res = (res + o.quantity) % mod;
        }

        for (Order o : buyBacklog) {
            res = (res + o.quantity) % mod;
        }

        return res;
    }

}

class Order {
    int price;
    int quantity;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }
}
