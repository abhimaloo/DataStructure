package com.leetcode2019.easy;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddress {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet();
        for (String email : emails) {
            // find the string before domain name
            String[] domainAndName = email.split("@", 2);
            String mail = domainAndName[0];
            String domain = domainAndName[1];
            // Now first evaluate the period
            String[] parts = mail.split("\\.");
            String joined = "";
            for (String part : parts) {
                joined += part;
            }

            // now take care of +
            joined = joined.split("\\+", 2)[0];
            set.add(joined + "@" + domain);
        }

        return set.size();
    }

    public static void main(String[] args) {
        UniqueEmailAddress obj = new UniqueEmailAddress();
        System.out.println(obj.numUniqueEmails(new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"}));
    }
}
