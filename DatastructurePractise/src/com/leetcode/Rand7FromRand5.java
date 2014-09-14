package com.leetcode;

import java.util.Random;

/**
 * Generate Random number among 1 to 7 from a random generator which can generate random number between 1 to 5
 * Created by abhimaloo on 9/12/14.
 */
public class Rand7FromRand5 {

    public static int rand5() {
        Random rand = new Random();
        return rand.nextInt(5) +1;
    }

    /**
     * technique is called accept - reject algorithm. Very nice explaination - https://www.youtube.com/watch?v=sKLt-NcPXFE
     * gist is  -
     * rand(5) can generate rand(25) by rolling two 5 sided dies togather and counting the number of permutations which are 25
     * in terms of code         "5*(rand(5) -1) + rand5() -1 " will generate all the numbers from 0 to 24
     *
     * from rand25 to rand7 is easy, reject anything which is greater then 21 while anything less than 21 can be taken mod of 7 with
     * rand7 = rand25 %7 +1;
     * rand7 generates numbers from 1 to 7.
     *
     * @return
     */
    public static int rand7() {

        // first term will generate random number between 0 to 4 which gets multiplied by 5. so it generates number from (0, 5, 10, 15, 20)
        // second term genrates numbers from(0,1,2,3 , 4) .. since they both are independent events you can generate all the numbers between 0 to 24
        int rand25 = 5 *(rand5() -1) + (rand5()-1);
         // rejecting the input
        if(rand25 >21) {
           return  rand7();
        } else {
            // converting rand25 to rand7
            return rand25 % 7 +1;
        }

    }

    public static void main(String[] args) {
        for( int i = 0; i< 1000; i++) {
            int num = rand7();
            if(num <1 && num >7) {
                System.out.println("failed");
            }
        }
        System.out.println("Passed");

    }

}
