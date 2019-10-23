package com.leetcode2019.easy;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoggerRateLimiter {
    Map<String, Integer> map = null;
    int limit = 10;
    int lastSecond = 0;

    /**
     * Initialize your data structure here.
     */
    public LoggerRateLimiter() {
        this.map = new LinkedHashMap<String, Integer>() {
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                if (lastSecond - eldest.getValue() > 10) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        this.lastSecond = timestamp;
        if (!this.map.containsKey(message) || timestamp - map.get(message) >= 10) {
            this.map.put(message, timestamp);
            return true;
        } else {
            return false;
        }
    }

}
