package com.leetcode2019.medium;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/flatten-nested-list-iterator/submissions/
 */
public class NestedIterator {
    List<Integer> list = new ArrayList();
    private int idx = -1;

    public NestedIterator(List<NestedInteger> nestedList) {
        for (NestedInteger item : nestedList) {
            build(list, item);
        }
        if (list.size() > 0) {
            idx = 0;
        }
    }

    private void build(List<Integer> list, NestedInteger item) {
        if (item.isInteger()) {
            list.add(item.getInteger());
            return;
        }
        for (NestedInteger subItems : item.getList()) {
            build(list, subItems);
        }
    }


    public Integer next() {
        if (idx < list.size()) {
            return list.get(idx++);
        }

        return null;
    }

    public boolean hasNext() {
        return idx != -1 && idx != list.size();
    }
}

interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    public Integer getInteger();

    public List<NestedInteger> getList();
}