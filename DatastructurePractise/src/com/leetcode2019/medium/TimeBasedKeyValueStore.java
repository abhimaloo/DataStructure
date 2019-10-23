package com.leetcode2019.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
https://leetcode.com/problems/time-based-key-value-store/
 */
public class TimeBasedKeyValueStore {
    Map<String, TreeMap<Integer, String>> map;

    /**
     * Initialize your data structure here.
     */
    public TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<>());
        }
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (map.containsKey(key)) {
            Map.Entry<Integer, String> lesserValue = map.get(key).floorEntry(timestamp);
            if (lesserValue != null) {
                return lesserValue.getValue();
            } else {
                if (map.get(key).containsKey(timestamp)) {
                    return map.get(key).get(timestamp);
                }
            }
        }
        return "";
    }
}
