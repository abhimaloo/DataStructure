package com.leetcode2019.hard;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/cracking-the-safe/
 */
public class CrackSafe {
    /*
    Very tricky -
    we know than solution would be k^n in order to cover all the possible combination of n length passwork with 0..k-1 letters
     We are going to do DFS(backtracking)
     */
    public static String crackSafe(int n, int k) {
        double target = Math.pow(k, n);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append('0');
        }
        Set<String> set = new HashSet<>();
        set.add(result.toString());
        dfs(result, (int) target, set, n, k);

        return result.toString();
    }

    /*
    Idea is to pick the last n-1 character and add every possible value of k and back track

     */
    public static boolean dfs(StringBuilder sb, int target, Set<String> visited, int n, int k) {
        // exit condition is special. We need to check whether we have seen k^n string so far ; if yes we are done
        if (visited.size() == target) return true;

        String prevWithouLastChar = sb.substring(sb.length() - n + 1, sb.length());

        for (int i = 0; i < k; i++) {
            String candidate = prevWithouLastChar + i;
            if (!visited.contains(candidate)) {
                visited.add(candidate);
                sb.append(i);
                if (dfs(sb, target, visited, n, k)) {
                    return true;
                } else {
                    visited.remove(candidate);
                    sb.delete(sb.length() - 1, sb.length());
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(crackSafe(1, 2));
    }
}
