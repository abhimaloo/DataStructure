package com.revision;

/**
 * Created by abhimaloo on 9/16/14.
 */
public class AddTwoNumbersInBinary {

    /**
     * Trick is to follow the binary math -
     * iterate through every bit and set the value of x , y and carry accordingly and set the correct bit in sum appropriately
     * @param a
     * @param b
     * @return
     */
    public static int sum(int a, int b) {
        int carry = 0;
        int sum = 0;
        for( int bit = 0 ; bit < 32; bit++) {
            int x = ((1<< bit) & a) != 0 ? 1: 0 ;
            int y = ((1 << bit) & b) != 0 ? 1: 0;
            int z = x^y;
            if(z == 0) {
                z = carry;


                carry = x == 0 ? 0 : 1;
            } else {
                z = carry == 0? 1 : 0;

                carry = carry == 0? carry : 1;
            }
            sum  = sum | (z << bit);



        }


       return sum;

    }

    public static void main(String[] args) {
        System.out.println(sum(5,3));
    }
}
