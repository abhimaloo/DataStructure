package com.maloo.streamingalgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhimaloo on 7/6/14.
 */
public class FrequencyFindDeterministic {

    public static void findMostFrequentItems(int[] a, int k) {
        Map<Integer,Integer> frequencyMap = new HashMap<>();

        for(int i =0; i < a.length; i ++) {
            if(frequencyMap.containsKey(a[i])) {

                frequencyMap.put(a[i],frequencyMap.get(a[i]) +1 );
            } else if(frequencyMap.size() < k){
                frequencyMap.put(a[i],1);
            } else {

                for(Integer key: frequencyMap.keySet()){
                    frequencyMap.put(key, frequencyMap.get(key) -1);
                    if(frequencyMap.get(key) <1){
                        frequencyMap.remove(key);
                    }

                }
            }
        }


    }
}
