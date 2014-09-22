package com.revision;

/**
 * Created by abhimaloo on 9/21/14.
 */
public class FindMissingInteger {
    public static int [] input = {0, 1, 2, 3, 4, 5, 6, 7, 8,10, 11 , 12, 13, 14, 15, 16, 17, 18,19,20};

    public static int findMissing(int[] data){
        int[] arr = new int[Integer.MAX_VALUE>>6];

        for( int i = 0; i< data.length; i++){
            arr[data[i]/32] |= 1 << data[i]%32;
        }

        for( int i = 0; i< arr.length; i++){
            for(int j = 0; j< 32; j++) {
                if((arr[i]& 1<<j) == 0){
                    return i*8 + j;
                }
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        System.out.println(findMissing(input));
    }

}
