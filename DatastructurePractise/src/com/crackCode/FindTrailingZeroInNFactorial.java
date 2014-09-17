package com.crackCode;

/**
 * Created by abhimaloo on 9/16/14.
 */
public class FindTrailingZeroInNFactorial {

    public static int n = 26 ;

    /**
     * trick is to count number of 5's appearing in the factor of every element of n!
     * @param n
     * @return
     */
    public static int findTrailingZeros(int n) {

        int fiveCount = 0;

        while(n >= 5) {
            if(n % 5 == 0) {
                int numFive = n /5;
                fiveCount++;

                while(numFive%5 == 0 && numFive >= 5) {
                    fiveCount++;
                    numFive = numFive/5;
                }


            }
            n--;

        }

        return fiveCount;

    }

    public static int numZeros(int num) {
         int count = 0;
         if (num < 0) {
            System.out.println("Error");
            return 0;
            }
        for (int i = 5; num / i > 0; i *= 5) {
            count += num / i;
            }
         return count;
     }

    public static void main(String[] args) {
        System.out.println(findTrailingZeros(n));
        System.out.println(numZeros(n));
    }

}
