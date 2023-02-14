package com.leetcode2022.revision;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class KeyValueStore {
    private Map<String, String> currentStore;
    private Map<String, String> globalStore = new HashMap<>();
    Stack<Map<String, String>> transactionStack = new Stack<>();

    public KeyValueStore() {
        currentStore = globalStore;
    }

    public String get(String key) {
        return currentStore.get(key);
    }

    public void set(String key, String value) {
        currentStore.put(key, value);
    }

    public String delete(String key) {
        return currentStore.remove(key);
    }

    public void beginTransaction() {
        Map<String, String> transactionStore = new HashMap<>();
        transactionStore.putAll(currentStore);
        transactionStack.push(currentStore);
        currentStore = transactionStore;
    }

    public void commitTransaction() {
        if (currentStore == globalStore) {  // its a no-op
            return;
        }
        Map<String, String> parentTransactionStore = transactionStack.pop();
        parentTransactionStore.putAll(currentStore);
        currentStore.clear();
        currentStore = parentTransactionStore;
    }

    public void rollback() {
        if (currentStore == globalStore) {  // its a no-op
            return;
        }

        Map<String, String> parentTransactionStore = transactionStack.pop();
        currentStore.clear();
        currentStore = parentTransactionStore;
    }

    public static void main(String[] args) {
        KeyValueStore db = new KeyValueStore();
        db.set("k0", "v0");
        // System.out.println(db.get("k0"));
        db.beginTransaction();
        db.set("k1", "v1");
        System.out.println(db.get("k1"));
        db.rollback();
        System.out.println(db.get("k1"));

    }
}
