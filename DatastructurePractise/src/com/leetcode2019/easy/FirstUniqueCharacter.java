package com.leetcode2019.easy;

import java.util.LinkedHashMap;
import java.util.Map;

/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.


 */
public class FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        int firstUnique = -1;
        if (s.length() == 0) return firstUnique;

        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), -1);
            } else {
                map.put(s.charAt(i), i);
            }
        }


        for (int i : map.values()) {
            if (i != -1) {
                firstUnique = i;
                break;
            }
        }

        return firstUnique;
    }
}
