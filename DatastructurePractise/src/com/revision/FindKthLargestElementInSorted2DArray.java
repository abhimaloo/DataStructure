package com.revision;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given a 2d array or matrix which is sorted by its rows and columns. Find the kth largest element from this matrix.
 *
 * Created by abhimaloo on 9/17/14.
 */
public class FindKthLargestElementInSorted2DArray {

    public static int [][] input =  {{1,2,3,4},
                                    {5,6,7,8},
                                    {9,10,11,12},
                                    {13,14,15,16}};

    static  class Pair {
        public int x;
        public int y ;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if(o == null){
                return false;
            }
            if(!(o instanceof Pair)) {
                return false;
            }

            Pair p = (Pair) o;
            if(p.x == this.x && p.y == this.y) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 31*hash + x;
            hash = 31*hash + y;
            return hash;
        }
    }

    public static int  findKthLargest(final int[][] input, int k) {

        PriorityQueue<Pair> heap = new PriorityQueue<>(k, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
               return  -1* Integer.compare(input[o1.x][o1.y], input[o2.x][o2.y]);
            }
        });

        int x = input.length - 1;
        int y = input[0].length - 1;

        heap.add(new Pair(x,y));
        for( int i = 0; i< k-1; i++) {
            Pair largest = heap.remove();
            if(largest.x - 1 >=0) {
                if(!heap.contains(new Pair(largest.x -1,largest.y))) {
                    heap.add(new Pair(largest.x -1,largest.y));
                }


            }
            if(largest.y -1 >= 0){
                if(!heap.contains(new Pair(largest.x,largest.y -1))) {
                    heap.add(new Pair(largest.x,largest.y -1));
                }

            }

        }

        return input[heap.peek().x][heap.peek().y];

    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(input, 1));
    }


}
