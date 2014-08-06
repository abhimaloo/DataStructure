package com.interview.string;

import com.interview.linkedlist.DoublyLinkedList;
import com.interview.linkedlist.DoublyNode;

import java.util.HashMap;

/**
 * Given a stream of characters, find the first non-repeating character from stream.
 * You need to tell the first non-repeating character in O(1) time at any moment.

 * http://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
 * Created by abhimaloo on 8/5/14.
 */
public class FindFirstNonRepeatingCharacterInAStream {
   public static char[] stream = {'g','e','e','k','g'};
    public static HashMap<Character, DoublyNode<Character>> hash = new HashMap<>();
    public static DoublyLinkedList<Character> list = new DoublyLinkedList<>();


    /**
     * trick is to have a hash map and doubly linked list
     * hashMap will keep the character to linkedListNode mapping
     * linkedList will keep chronological order of the characters arrrived
     * if hashmap finds a duplicate it deletes it from the doubly linked list
     *
     * @param input
     * @return
     */
    public static char findfirstNonRepeatedChar(char input) {

        if(hash.containsKey(Character.valueOf(input))) {
            DoublyNode<Character> node = hash.get(Character.valueOf(input));
            //delete the node .. take care of head and tail
            list.remove(node);
        } else {
            DoublyNode<Character> node = list.insert(input);
            hash.put(Character.valueOf(input), node);
        }

        return list.head.data;

    }

    public static void main(String[] args) {

        for( int i=0;i<stream.length; i++) {
            System.out.println(findfirstNonRepeatedChar(stream[i]));
        }
    }
}
