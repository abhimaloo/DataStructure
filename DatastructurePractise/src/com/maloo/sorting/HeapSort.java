package com.maloo.sorting;

/**  This is an implementation of Heap(Priority Queue) based sorting
 * Created by abhishekm787 on 7/3/14.
 */
public class HeapSort<T extends  Comparable<T>> {

    /**
     * This class models a binary heap
     * It offers operations like insert, peek and removeMin
     * @param <T>
     */
    public static class BinaryHeap<T extends  Comparable<T>> {
        /**
         * This stores the heap nodes in an array
         * root is stored at "1" index
         * all the children of a particular root at index i
         * are stored at 2*i and 2*i+1 left and right child respectively
         *
         * parent of a child at index i can be found at floor of i/2
         */
        public Object[] store = new Object[15];

        /**
         * this variable keeps the location of the last element on the heap in level order
         */
        public int tail = 0;

        /**
         * it returns the minimum node of the heap
         * @return
         */
        public T peek(){
            return (T)store[1];
        }

        /**
         * checks whether the heap is empty or not
         * @return
         */
        public boolean isEmpty(){
            return tail == 0;
        }

        /**
         * this method inserts the incoming node to the last node of the heap first
         * then it bubbles up the last node till the heap invariant is met
         * @param data  - its the object which needs to be inserted in the heap
         */

        public void insert(T data) {
            //save the data to the last available location
            store[++tail] = data;

            int index = tail;
            //bubble up with heap
            //if child is smaller than parent
            while(index/2>0 && ((T)store[index]).compareTo((T)store[index/2]) <0){
                //swap the entries of child with parent
                Object temp = store[index/2];
                store[index/2] = store[index];
                store[index] = temp;
                index = index/2;
            }

        }

        /**
         * very important method .. it removes the minimum entry from the heap
         * It first replaces the root with tail element
         * and then bubbles down the new root to its appropriate position by
         * swapping it with the minimum of its left or right child till the heap invariant is maintained
         * @return
         */
        public T removeMin() {
            T response = (T)store[1];

            //insert the last entry to the root if heap does not only contains root
            if(tail!=1){
                store[1] = store[tail];
            }
            //nullify the tail node since its copied to the root
            store[tail] = null;
            //reduce the tail
            tail --;

            int index = 1;
            // bubble down the root to satisfy heap order
            //check if root is bigger than left or right child
            while((index*2<=tail && (((T)store[index]).compareTo((T)store[index*2]) > 0)) || (index*2+1<=tail && (((T)store[index]).compareTo((T)store[index*2+1]) > 0)) ) {
                int minChildIndex = -1;
                T minChildValue = null;
                //if parent is bigger than left child
                if(((T)store[index]).compareTo((T)store[index*2]) > 0){
                    //assign minChildIndex and minChildValue of the left child
                    minChildIndex = index*2;
                    minChildValue = (T)store[minChildIndex];
                }
                //if right child is present, parent is bigger than the right child and the left child is even bigger then right child..
                // assign the right child to be the minimum one
                if(index*2+1 <=tail && ((T)store[index*2+1]).compareTo((T)store[index*2]) < 0 && ((T)store[index]).compareTo((T)store[index*2+1]) > 0){
                    //assign minChildIndex and minChildValue of the right child
                    minChildIndex = index*2 +1;
                    minChildValue = (T)store[minChildIndex];
                }
                //swap the parent with minimum child
                store[minChildIndex] = store[index];
                store[index] = minChildValue;
                index = minChildIndex;
            }
            return response;
        }
    }

    /**
     * very useful and straightforward
     * heapify every element of the incoming array
     * now keeo removing the min of the heap and
     * place it in input array in order
     * @param objects
     */

    public void heapSort(T[] objects) {
        BinaryHeap<T> heap = new BinaryHeap<>();
        for(T object: objects) {
           // heapify every element
           heap.insert(object);
        }

        // start arranging the minimum elements from heap
        for(int i=0; i <objects.length; i++){
            objects[i] = heap.removeMin();
        }

    }

    public static void main(String[] args) {
        HeapSort<Integer> heapSort = new HeapSort<>();
        Integer[] input = {7,5,11,9,2,15,1,3};

        heapSort.heapSort(input);

        for(int i=0; i <input.length; i++){
            System.out.println(input[i]);
        }

    }

}
