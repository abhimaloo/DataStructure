package com.interview.arrays;

import java.util.*;

/**
 * Given an array of integers, sort the array according to frequency of elements.
 * For example, if the input array is {2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12},
 * then modify the array to {3, 3, 3, 3, 2, 2, 2, 12, 12, 4, 5}.
 * Created by abhishekm787 on 7/15/14.
 *
 * Complexity is O(nlogn)
 */
public class SortArrayByFrequency {
    public static int []input = {2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12};

    public static int[] sortByFrequencyUsingMap(int[] input){
        //used to hold frequency and keys
        Map<Integer,Integer> frequencyMap = new HashMap<>();

        // hashMap keeping frequency
        for(int i=0; i<input.length; i ++){
            if(frequencyMap.containsKey(input[i])){
                frequencyMap.put(input[i], frequencyMap.get(input[i]) +1 );
            } else {
                frequencyMap.put(input[i], 1 );
            }
        }

        // pour the Map in a arryayList and sort it by value
        List<Map.Entry<Integer,Integer>> orderedList = new ArrayList<>(frequencyMap.entrySet());
        Collections.sort(orderedList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return  o1.getValue().compareTo(o2.getValue());
            }
        });


        int[] result = new int[input.length];
        int resultIndex =0;
        // reconstruct the array
        for(int i=orderedList.size()-1; i>=0; i--){
           int value = orderedList.get(i).getValue();
           while(value>0){
               result[resultIndex++] = orderedList.get(i).getKey();
               value--;
           }

        }
       return result;

    }

    public static void print(int [] input) {
        for (int i = 0; i < input.length; i++) {
            System.out.print(" "+ input[i]+ " ");

        }
    }

    public static void main(String[] args) {
        print(sortByFrequencyUsingMap(input));
    }


}
