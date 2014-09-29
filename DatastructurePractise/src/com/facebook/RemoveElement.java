package com.facebook;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class RemoveElement {

    public static int[] input = {1, 2, 3,4, 3, 2, 3};

    public static int removeElement(int[] input, int element) {
        if(input == null || input.length == 0){
            return -1;
        }
        int index = 0;

        for(int i = 0; i< input.length; i++) {
           if(input[i] != element) {
               input[index++] = input[i];
           }
        }

        return index;

    }

    public static void main(String[] args) {
        System.out.println(removeElement(input, 3));
    }
}
