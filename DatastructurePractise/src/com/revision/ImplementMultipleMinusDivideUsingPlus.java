package com.revision;

/**
 * Created by abhimaloo on 9/15/14.
 */
public class ImplementMultipleMinusDivideUsingPlus {

    public static int negate(int a) {
        int result = 0;
        int d = a < 0 ? 1 :-1;
        while( a != 0) {
            result += d;
            a += d;
        }
        return result;

    }


    public static int subtract(int a , int b) {
        return a + negate(b);
    }


   public static boolean hasDifferenceSign(int a, int b) {
       return a > 0 && b < 0 || b > 0 && a < 0 ;
   }

   public static int abs(int a) {
       return a < 0? negate(a): a;
   }

   public static int multiply(int a , int b) {
       int result = 0;
       if(a < b) {
           multiply(b, a);
       }

       for( int i = abs(b) ; i > 0; i = subtract(i, 1)) {
           result += a;
       }

       return b < 0? negate(result) : result;
   }

    public static int divide(int a, int b) {
        if(b == 0) {
            throw new ArithmeticException("Divide By Zero");
        }
        if(a ==0) {
            return 0;
        }


        int quetient = 0;
        int divisor = negate(abs(b));
        int divident = abs(a);
        for( ; divident >= abs(divisor) ; divident += divisor) {
            ++quetient;
        }

        return hasDifferenceSign(a, b) ? negate(quetient) : quetient;
    }







    public static void main(String[] args) {
        System.out.println(divide(10, 2));
        System.out.println(Integer.MAX_VALUE);
    }
}
