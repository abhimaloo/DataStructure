package com.leetcode2019.medium;

import java.util.*;

/*
https://leetcode.com/problems/network-delay-time/
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int N, int K) {
        if (times.length == 0 || N == 1) return 0;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] time : times) {
            map.putIfAbsent(time[0], new ArrayList<>());
            map.get(time[0]).add(new int[]{time[1], time[2]});
        }

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Integer[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        queue.offer(new Integer[]{0, K});
        int shortestCost = 0;
        while (!queue.isEmpty()) {
            Integer[] current = queue.remove();
            int dist = current[0];
            int node = current[1];
            if (visited.contains(node)) continue;
            visited.add(node);
            shortestCost = dist;
            N--;
            if (map.containsKey(node)) {
                for (int[] next : map.get(node)) {
                    if (!visited.contains(next[0])) {
                        queue.offer(new Integer[]{dist + next[1], next[0]}); // add distance till this node + distance of next node
                    }
                }
            }
        }

        return N == 0 ? shortestCost : -1;
    }

    public static void main(String[] args) {
        NetworkDelayTime obj = new NetworkDelayTime();
        System.out.println(obj.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 2}}, 3, 1));
    }
}
