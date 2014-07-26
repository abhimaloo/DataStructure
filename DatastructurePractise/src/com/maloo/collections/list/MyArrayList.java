package com.maloo.collections.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by abhishekm787 on 7/25/14.
 */
public class MyArrayList<T> {
    private T[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private transient  int modCount = 0;

    public MyArrayList() {
        data = (T[])new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(int initialCapacity) {
        data = (T[])new Object[initialCapacity];
        size = 0;
    }

    public boolean add(T element) {
        modCount++;
        if(size == data.length){
            ensureCapacity(size+1);
        }
        data[size++] = element;
        return true;
    }

    private void ensureCapacity(int newSize) {
        T[] newData = (T[])new Object[newSize];
        System.arraycopy(data,0,newData,0,data.length);
        this.data = newData;
    }

    public T get(int index) throws IndexOutOfBoundsException{
        checkBound(index);
        return data[index];
    }

    public T remove(int index) throws  IndexOutOfBoundsException{
        checkBound(index);
        modCount++;
        T removedData = data[index];
        // if index is not equals to last index
        if(index != --size){
          // copy data form index +1 to end into index till it fits
          // very neat trick .. learn it
          System.arraycopy(data,index+1,data,index,size - index);
        }
        //mark the last element to be null
        data[size] = null;
        return removedData;

    }


    public Iterator<T> iterator(){
       return new Itr();
    }


    private void checkBound (int index) throws IndexOutOfBoundsException{
        if(index > size){
            throw new IndexOutOfBoundsException();
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * trick is to keep two pointers for location
     * and one pointer to check for modification
     * cursor points to the next element
     * lastRet points to the last returned element
     */
    private class Itr implements  Iterator<T>{
        private int cursor = 0;
        private int lastRet = -1;
        private int expectedModCount = modCount;

        /**
         * check if cursor has hit the size limit
         */
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        /**
         * this method gets you the next available element
         * trick is to update the cursor properly
         * set the lastret to last returned element
         * @return
         */
        @Override
        public T next() {
            //check for conurrent modification
            checkForConcurrentModification();
            // local pointer to superclass's data
            T[] elements = MyArrayList.this.data;
            //read cursor
            int i = cursor;
            // if cursor is greater than element length which mean someone has deleted an element from backing object []
            if(i>elements.length){
                throw new ConcurrentModificationException();
            }
            //update the cursor
            cursor = i+1;
            //update the lastRet variable and return the appropriate value
            return elements[lastRet = i];
        }

        private void checkForConcurrentModification() {
            if(modCount!=expectedModCount){
               throw new ConcurrentModificationException();
            }
        }

        /**
         * trick is to remove the last returned object from backing []
         * update the lastRet to -1 and cursor to lastRet.
         * Keep checking for concurrent modifications as well
         */
        @Override
        public void remove() {
           if(lastRet < 0) {
               throw new IllegalStateException();
           }

           checkForConcurrentModification();
           try {
               MyArrayList.this.remove(lastRet);
               cursor = lastRet;
               lastRet = -1;
               expectedModCount = modCount;
           } catch (IndexOutOfBoundsException ex) {
               throw new ConcurrentModificationException();
           }

        }
    }


    public static void main(String[] args) {
        MyArrayList<String> list  = new MyArrayList<>();
        list.add("abhishek");
        list.add("maloo");
        list.add("is");
        list.add("my");
        list.add("name");

        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String next =  itr.next();
            System.out.println(next);
        }


    }
}
