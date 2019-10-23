package com.leetcode2019.medium;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/fruit-into-baskets/
 */
public class FruitIntoBaskets {
    public static int totalFruit(int[] tree) {
        int left = 0;
        int maxFruit = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < tree.length; i++) {
            if (map.containsKey(tree[i])) {
                map.put(tree[i], map.get(tree[i]) + 1);
            } else {
                if (map.size() == 2) {
                    maxFruit = Math.max(maxFruit, i - left);

                    // go to the next element which is not same as left
                    int j = left;
                    while (j < i) {
                        if (map.get(tree[j]) == 1) {
                            map.remove(tree[j]);
                            break;
                        } else {
                            map.put(tree[j], map.get(tree[j]) - 1);
                        }
                        j++;
                    }
                    left = j + 1;
                }
                map.put(tree[i], 1);
            }
        }

        maxFruit = Math.max(maxFruit, tree.length - left);

        return maxFruit;
    }

    public static void main(String[] args) {
        System.out.println(totalFruit(new int[]{1, 0, 1, 4, 1, 4, 1, 2, 3}));
    }
}
