package com.leetcode2019.medium;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabelII {
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        if (S == null || S.length() == 0) {
            return result;
        }

        int[] map = new int[26];  // record the last index of the each char

        for (int i = 0; i < S.length(); i++) {
            map[S.charAt(i) - 'a'] = i;
        }
        int start = 0;
        int last = 0;
        for (int i = 0; i < S.length(); i++) {
            last = Math.max(last, map[S.charAt(i) - 'a']);
            if (i == last) { // cut the partition
                result.add(last - start + 1);
                start = last + 1;
            }
        }

        return result;
    }
}
