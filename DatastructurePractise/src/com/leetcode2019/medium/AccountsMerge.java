package com.leetcode2019.medium;

import java.util.*;

/*
https://leetcode.com/problems/accounts-merge/
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input:
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation:
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 */
public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        int[] roots = new int[accounts.size()];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;

        }

        Map<String, Integer> map = new HashMap<>();
        int idx = 0;
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                if (!map.containsKey(account.get(i))) {
                    map.put(account.get(i), idx);
                } else {
                    int lastAccount = map.get(account.get(i));
                    union(roots, lastAccount, idx);
                }
            }
            idx++;
        }

        Map<Integer, TreeSet<String>> resultMap = new HashMap<>();
        for (Integer accountId : map.values()) {
            int parentAccount = find(roots, accountId);
            resultMap.putIfAbsent(parentAccount, new TreeSet<>());
            resultMap.get(parentAccount).addAll(accounts.get(accountId).subList(1, accounts.get(accountId).size()));
        }

        for (int parent : resultMap.keySet()) {
            List<String> res = new ArrayList<>();
            res.add(accounts.get(parent).get(0));
            res.addAll(resultMap.get(parent));
            result.add(res);
        }

        return result;
    }

    public void union(int[] root, int i, int j) {
        int iParent = find(root, i);
        int jParent = find(root, j);
        if (iParent != jParent) {
            root[iParent] = jParent;
        }
    }

    public int find(int[] root, int i) {
        while (root[i] != i) {
            i = root[i];
        }
        return i;
    }

    public static void main(String[] args) {
        AccountsMerge obj = new AccountsMerge();
        List<List<String>> accounts = Arrays.asList(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"), Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"), Arrays.asList("Mary", "mary@mail.com"), Arrays.asList("John", "johnnybravo@mail.com"));
        obj.accountsMerge(accounts);
    }
}
