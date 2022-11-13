package com.leetcode2022.prep;

import com.google.common.collect.ImmutableList;

import java.util.*;

public class RecurringTransactions {
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

    class ExactTransactionComparator implements Comparator<Transactions> {

        @Override
        public int compare(Transactions left, Transactions right) {
            int nameComparision = left.company.compareTo(right.company);
            int amountComparision = Double.compare(left.amount, right.amount);
            int timestampComparision = Integer.compare(left.timestamps, right.timestamps);

            // sort order -> name, timestamps, amount
            if (nameComparision == 0) {
                if (timestampComparision == 0) {
                    return amountComparision;
                } else {
                    return timestampComparision;
                }
            } else {
                return nameComparision;
            }
        }
    }


    public List<String> getCompaniesWithRecurringTransactions(Transactions[] transactions) {
        if (transactions == null || transactions.length == 0) {
            return null;
        }

        List<String> output = new ArrayList<>();

        //Arrays.sort(transactions, new ExactTransactionComparator());
        Map<String, List<Transactions>> transactionMap = new HashMap<>();

        for (Transactions t : transactions) {
            List<Transactions> list = transactionMap.getOrDefault(t.company, new ArrayList<>());
            list.add(t);
            transactionMap.put(t.company, list);
        }

        for (Map.Entry<String, List<Transactions>> entry : transactionMap.entrySet()) {
            if (entry.getValue().size() >= 3 && isRecurring(entry.getValue())) {
                output.add(entry.getKey());
            }
        }

        return output;
    }

    public static boolean isRecurring(List<Transactions> transactions) {
        Collections.sort(transactions, (left, right) -> {
            int amountComparator = Double.compare(left.amount, right.amount);
            int timestampComparator = Integer.compare(left.timestamps, right.timestamps);
            if (amountComparator == 0) {
                return timestampComparator;
            } else {
                return amountComparator;
            }
        });

        double previousAmount = -1;
        int previosTimestamp = 0;
        int previousDeltaTimestamp = 0;
        int similarityTarget = 0;

        for (Transactions t : transactions) {
            if (t.amount == previousAmount) {
                if (t.timestamps - previosTimestamp == previousDeltaTimestamp) {
                    similarityTarget++;
                    previosTimestamp = t.timestamps;
                } else {
                    previousDeltaTimestamp = t.timestamps - previosTimestamp;
                    previosTimestamp = t.timestamps;
                    similarityTarget = 1;
                }
            } else {
                previousAmount = t.amount;
                previousDeltaTimestamp = 0;
                previosTimestamp = t.timestamps;
                similarityTarget = 1;
            }
            if (similarityTarget == 2) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        RecurringTransactions r = new RecurringTransactions();

        Transactions[] input = ImmutableList.of(
                new RecurringTransactions.Transactions("Netflix", 100, 0),
                new RecurringTransactions.Transactions("Netflix", 100, 10),
                new RecurringTransactions.Transactions("Netflix", 100, 20),
                new RecurringTransactions.Transactions("Amazon", 100, 10),
                new RecurringTransactions.Transactions("Sprint", 100, 10),
                new RecurringTransactions.Transactions("Sprint", 100, 20),
                new RecurringTransactions.Transactions("Sprint", 100, 30),
                new RecurringTransactions.Transactions("Netflix", 50, 50)
        ).toArray(new Transactions[8]);

        List<String> companies = r.getCompaniesWithRecurringTransactions(input);
        for (String company : companies) {
            System.out.println(company);
        }
    }

}
