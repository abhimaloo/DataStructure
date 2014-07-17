package com.interview.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of numbers, arrange them in a way that yields the largest value.
 * For example, if the given numbers are {54, 546, 548, 60},
 * the arrangement 6054854654 gives the largest value
 * Created by abhishekm787 on 7/16/14.
 */
public class ArrangeNumberToMakeBiggerNumber {
    public static int[] input = {54, 546, 548, 60};

    /**
     * trick is to arrange in decreasing order
     * compare xy with yx and return the bigger element
     * @param input
     * @return
     */
    public static String arrange(int[] input){
        List<Integer> numbers  = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            numbers.add(input[i]);
        }

        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Integer.parseInt(String.valueOf(o1)+String.valueOf(o2)) < Integer.parseInt(String.valueOf(o2)+String.valueOf(o1))){
                    return 1;
                } else if(Integer.parseInt(String.valueOf(o1)+String.valueOf(o2)) > Integer.parseInt(String.valueOf(o2)+String.valueOf(o1))){
                   return -1;
                } else {
                    return 0;
                }

            }
        });

        String response = "";
        for (int i = 0; i < numbers.size(); i++) {
            response +=  numbers.get(i);

        }
        return response;


    }

    public static void main(String[] args) {
        System.out.println(arrange(input));
    }

}
