package com.interview.string;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string and a positive integer d. Some characters may be repeated in the given string.
 * Rearrange characters of the given string such that the same characters become d distance away from each other.
 *
 * Ex - Input - "aacbbc", d = 3
 * Output - "abcabc"
 *
 * http://www.geeksforgeeks.org/rearrange-a-string-so-that-all-same-characters-become-at-least-d-distance-away/
 * Created by abhishekm787 on 8/5/14.
 */
public class RearrangeStringToMakeSameCharacterDDistanceAway {

    public static String input = "aacbbc";

    /**
     * Greedy Approach - place charcters with higher frequency first
     * Trick is to traverse the array and create a frequence character map.
     * Also side by side keep marking the characters in the array as '\0'(NULL Chars)
     * Now build a maxHeap out of this frequence character map on the basis of MAX frequency
     * Now remove maxElement of MaxHeap one by one and try putting the character at the firstposition where you find '\0'
     * Do this for the frequency of item i ..i+d , i +(f-1)d  ..
     *
     * @param input
     * @param distance
     * @return
     */
    public static String rearrangeString(String input, int distance) {
        Map<Character, Integer>  frequencyCounter = new HashMap<>();
        PriorityQueue<Pair> heap = new PriorityQueue<Pair>(input.length(), new Comparator<Pair>(){

            @Override
            public int compare(Pair o1, Pair o2) {
                return -1 *(o1.frequency.compareTo(o2.frequency));
            }
        });

        char[] inp = input.toCharArray();
        //frequency counting
        for( int i=0; i< inp.length; i++) {
            if(frequencyCounter.containsKey(inp[i])){
                frequencyCounter.put(inp[i], frequencyCounter.get(inp[i]) +1);
            } else {
                frequencyCounter.put(inp[i], 1);
            }
            //nulling the array
            inp[i] = '\0';
        }

        //maxHeapify the entries
        for(Map.Entry<Character,Integer> entry : frequencyCounter.entrySet()){
            heap.add(new Pair(entry.getKey(),entry.getValue()));
        }

        //iterate on heap in MaxHeap order
        while(!heap.isEmpty()){
            int i = 0;
            //find the first '\0' charcter
            for(; i<inp.length; i++){
                if(inp[i] =='\0'){
                    break;
                }
            }
            Pair p = heap.remove();
            // add the elements starting from that charcter
            for( int j = 0; j< p.frequency; j++){
                inp[i +(j*distance)] = p.a;
            }

        }

        return new String(inp);

    }

    static class Pair {
        Character a;
        Integer frequency;

        Pair(Character a, Integer frequency) {
            this.a = a;
            this.frequency = frequency;
        }
    }


    public static void main(String[] args) {
        System.out.println(rearrangeString(input, 3));
    }
}
