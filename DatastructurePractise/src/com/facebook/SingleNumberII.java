package com.facebook;

/**
 * Created by abhimaloo on 9/26/14.
 */
public class SingleNumberII {
    public static int[] input = {5,2,2,2,-1,-1,-1, 3,3,3 };

    /**
     * very tricky :
     * keep 3 integers to contain the bits of numbers which has occurred one time, two time and three time respectively
     *
     *
     * @param input
     * @return
     */
    public static int singleNumber(int[] input) {
        // three variables for keeping bits which occurred one time , two timeand three time
        int ones=0,twos=0,xthrees=0;
        // iterate over the array
        for(int i=0;i<input.length;i++){
            // check if bits in the array element is already set in ones' ..if yes, one should be promoted to two's
            twos|= ones & input[i];
            // bit will be set if it was not there(first time bit) and it will be unset if it was there already(promotion to two already happenned) so dont count this bit twice
            ones =  ones ^ input[i];
            // check if bit is present in both one and two .. that means that bit is coming third time .. for third timers flip all the bits opposite way, which means third time bit will be 0
            xthrees= ~(ones&twos);
            // clear the bit which got counted in thrid time from ones
            ones= ones&xthrees;
            // also clear the bit which got counted trird time from twos
            twos= twos&xthrees;
        }
        // return ones since it will contains the bits which came only once
        return ones;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(input));
    }

}
