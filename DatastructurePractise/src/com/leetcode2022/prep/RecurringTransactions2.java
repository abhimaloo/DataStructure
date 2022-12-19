package com.leetcode2022.prep;

import com.google.common.collect.ImmutableList;

import java.util.*;

public class RecurringTransactions2 {
    public static final int MIN_RECURRENCE = 3;

    public static class Transactions {
        private String company;
        private double amount;
        private int timestamps;

        public Transactions(String company, double amount, int timestamps) {
            this.company = company;
            this.amount = amount;
            this.timestamps = timestamps;
        }
    }

    public static List<String> getCompaniesWithRecurringTransactions(Transactions[] transactions) {
        if (transactions == null) // more validation regarding input
            return null;


        List<String> outputList = new ArrayList<>();

        //convert transactions to the map
        Map<String, Map<Double, List<Integer>>> transactionsByCompany = new HashMap<>();

        for (Transactions t : transactions) {
            Map<Double, List<Integer>> details = transactionsByCompany.getOrDefault(t.company, new HashMap<>());
            List<Integer> timestamps = details.getOrDefault(t.amount, new ArrayList<>());
            timestamps.add(t.timestamps);
            details.put(t.amount, timestamps);
            transactionsByCompany.put(t.company, details);
        }

        for (Map.Entry<String, Map<Double, List<Integer>>> i : transactionsByCompany.entrySet()) {
            if (hasRecurringTransactions(i.getValue(), MIN_RECURRENCE)) {
                outputList.add(i.getKey());
            }
        }


        return outputList;
    }

    public static boolean hasRecurringTransactions(Map<Double, List<Integer>> details, int minRecurrence) {
        for (Map.Entry<Double, List<Integer>> detail : details.entrySet()) {
            if (detail.getValue().size() < minRecurrence) {
                continue;
            } else {
                List<Integer> timestamps = detail.getValue();
                Collections.sort(timestamps);
                int delta = -1;
                int recurrenceMatch = 0;
                for (int i = 0; i < timestamps.size() - 1; i++) {
                    if (recurrenceMatch < minRecurrence - 2) {
                        if (timestamps.get(i + 1) - timestamps.get(i) == delta) {
                            recurrenceMatch++;
                        } else {
                            delta = timestamps.get(i + 1) - timestamps.get(i);
                        }
                    } else {
                        break;
                    }
                }
                if (recurrenceMatch == minRecurrence - 2) {
                    return true;
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {

        RecurringTransactions2.Transactions[] input = ImmutableList.of(
                new RecurringTransactions2.Transactions("Netflix", 100, 0),
                new RecurringTransactions2.Transactions("Netflix", 100, 10),
                new RecurringTransactions2.Transactions("Netflix", 100, 20),
                new RecurringTransactions2.Transactions("Amazon", 100, 10),
                new RecurringTransactions2.Transactions("Sprint", 100, 10),
                new RecurringTransactions2.Transactions("Sprint", 100, 20),
                new RecurringTransactions2.Transactions("Sprint", 100, 30),
                new RecurringTransactions2.Transactions("Netflix", 50, 50)
        ).toArray(new RecurringTransactions2.Transactions[8]);

        List<String> companies = getCompaniesWithRecurringTransactions(input);
        for (String company : companies) {
            System.out.println(company);
        }

    }
}
