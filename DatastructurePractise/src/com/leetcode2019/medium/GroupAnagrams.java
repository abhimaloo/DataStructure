package com.leetcode2019.medium;

import java.util.*;

/*
https://leetcode.com/problems/group-anagrams/
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            char[] unsorted = s.toCharArray();
            Arrays.sort(unsorted);
            String sorted = new String(unsorted);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
