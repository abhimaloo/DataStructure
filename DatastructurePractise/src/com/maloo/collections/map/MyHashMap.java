package com.maloo.collections.map;


/** Implementation of a hashmap
 * Chaining for conflicts
 * Created by abhishekm787 on 7/3/14.
 */
public class MyHashMap<K,V> {

    private static int DEFAULT_SIZE = 16;
    private static double DEFAULT_LOAD_FACTOR = 0.7;
    private double loadFactor;
    private Entry[] store ;
    private int size = 0;
    private int max_size = 0;

    public MyHashMap(){
          store = new Entry[DEFAULT_SIZE];
          max_size = DEFAULT_SIZE;
          loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int initialSize){
        store = new Entry[initialSize];
        max_size = initialSize;
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int initialSize, double loadFactor){
        store = new Entry[initialSize];
        max_size = initialSize;
        this.loadFactor = loadFactor;
    }

    public boolean contains(K key) {
        if(size>0) {
            //trick to avoid negative hash code and hash value more than Integer.MAX_VALUE
            int index = (key.hashCode() & 0x7fffffff) % max_size;
            Entry target = (Entry)store[index];
            while(target!=null){
                if(target.key.equals(key)){
                    return true;
                }
                target = target.next;
            }

        }

        return false;
    }


    public void put(K key, V value) {

        //if hashmap size is more than a certain load
        //its time to rehash the array
        if(size >= max_size*loadFactor ){
            //double the max_size
            max_size *= 2;
            //temporarily copy the old array
            Entry []temp = new Entry[max_size];
            System.arraycopy(store, 0, temp, 0, store.length);
            // create a clean copy of expanded old array
            store = new Entry[max_size];
            //reset the size of old array
            size = 0;

            //iterate the temporary copy and put all the values back in expanded old array
            for(int i=0; i<max_size/2; i ++){

                Entry<K,V> node = temp[i];
                //there could be a linked list inside
                while(node!=null){
                    //recursively call put
                    put(node.key, node.value);
                    node = node.next;
                }
            }
        }

        //trick to avoid negative hash code and hash value more than Integer.MAX_VALUE
        int hashcode = key.hashCode();
        int index = (hashcode & 0x7fffffff) % max_size;

        if(store[index]!=null){
            Entry<K,V> target = store[index];
            //update the value scenario
            while(target!=null){
                if(target.key.equals(key) && target.hashcode == hashcode){
                    target.value = value;
                    return;
                }
                target = target.next;
            }
            // create new entry
            Entry<K,V> newEntry = new Entry(hashcode,key,value,store[index]);
            store[index] = newEntry;
            size ++;

        } else {
            Entry<K,V> newEntry = new Entry(hashcode,key,value,null);
            store[index] = newEntry;
            size ++;
        }

    }


    public V get(K key) {
        int hashcode = key.hashCode();
        int index = (hashcode & 0x7fffffff) % max_size;
        if(store[index] ==null){
            return null;
        } else {
            Entry<K,V> target = store[index];
            while(target!=null){
                if(target.key.equals(key) && target.hashcode == hashcode){
                    return target.value;
                }
                target = target.next;
            }

        }

        return  null;
    }


    public static void main(String[] args) {
        MyHashMap<String,Integer> hashMap = new MyHashMap<>(10);

        hashMap.put("1",1);
        hashMap.put("2",2);
        hashMap.put("12",12);
        hashMap.put("22",22);
        hashMap.put("32",32);
        hashMap.put("42",42);
        hashMap.put("52",52);
        hashMap.put("62",62);
        hashMap.put("72",72);
        hashMap.put("10",10);

        System.out.println(hashMap.get("10"));


    }


    private class Entry<K,V> {
        public int hashcode;
        public K key;
        public V value;
        Entry<K,V> next;

        private Entry(int hashcode,K key, V value, Entry<K,V> next) {
            this.hashcode = hashcode;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
