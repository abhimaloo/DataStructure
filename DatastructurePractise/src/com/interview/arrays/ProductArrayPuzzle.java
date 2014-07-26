package com.interview.arrays;

/**
 * Given an array arr[] of n integers, construct a Product Array prod[] (of same size) such that
 * prod[i] is equal to the product of all the elements of arr[] except arr[i]
 *
 * arr[] = {10, 3, 5, 6, 2}
 * prod[] = {180, 600, 360, 300, 900}
 * Created by abhimaloo on 7/26/14.
 */
public class ProductArrayPuzzle {
    public static int[] input = {10, 3, 5, 6, 2};

    /**
     * trick is a two pass approach
     * in pass 1 add the product of left side elements to the array
     * in pass2 calculate the product of element on right of the index and
     * then multiple the product by existing left product in the array
     *
     * @param input
     * @return
     */
    public static int[] productArray(int [] input){
        int[] finalProduct = new int[input.length];


        //base case
        finalProduct[0] = 1;
        //base case
        finalProduct[input.length-1] = 1;

        int product = 1;
        //calculate the left side product first
        for( int i = 1; i < input.length; i++ ) {
            product *= input[i-1];
            finalProduct[i] = product;
        }
        product =1;
        //now calculate the right side product
        for(int i = input.length-2; i >= 0; i--) {
            product *= input[i+1];
            finalProduct[i] *= product;
        }

      return finalProduct;
    }

    public static void main(String[] args) {
        int[] product = productArray(input);
        for (int i = 0; i < product.length; i++) {
            System.out.println(product[i]);

        }
    }
}
