package com.leetcode2019.easy;

/**
 * https://leetcode.com/problems/count-primes/
 * <p>
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * Example:
 * <p>
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class CountPrimes {
    /**
     * Solution is a trick  - https://www.geeksforgeeks.org/sieve-of-eratosthenes/
     * Steps -
     * 1) Take an Boolean array of size n
     * 2) Now run a loop  i from 2 to n
     * 2.1) Run Another loop from square of i to n and mark all the elements which are divisibe by i ; jump j by i
     * 2.2) Find the next unmarked number and make it i
     * 3) Now count all the unmarked numbers in the Boolean Array. That would be our answer
     */
    public static int countPrimes(int n) {
        int primeCount = 0;
        if (n <= 2) {
            return primeCount;
        }

        boolean[] prefixes = new boolean[n];
        int i = 2;

        // Check i*i for underflow
        while (i < n && i * i > 0) {
            // jump j by i after finishing the loop
            for (int j = i * i; j < n; j += i) {
                if (j % i == 0) {
                    prefixes[j] = true;
                }
            }

            i++;
            while (i < n && prefixes[i]) {
                i++;
            }
        }

        // only check odd numbers, all even numbers are prime except 2
        for (int j = 3; j < n; j += 2) {
            if (!prefixes[j]) {
                primeCount++;
            }
        }
        return primeCount + 1;
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }
}
