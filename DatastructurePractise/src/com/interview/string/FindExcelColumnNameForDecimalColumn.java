package com.interview.string;

/**
 * MS Excel columns has a pattern like A, B, C, … ,Z, AA, AB, AC,…. ,AZ, BA, BB, … ZZ, AAA, AAB …..
 * etc. In other words, column 1 is named as “A”, column 2 as “B”, column 27 as “AA”.
 * Created by abhimaloo on 8/3/14.
 */
public class FindExcelColumnNameForDecimalColumn {
    public static String findExcelColumn(int number) {
        StringBuilder result = new StringBuilder();
        while(number/26 >0) {
           if(number%26 ==0){
               result.append("Z");
               number = (number/26) -1;
           } else{
               result.append((char)('A' + number%26 - 1));
               number = number/26;
           }


        }

        result.append((char)('A' + number%26 -1));
        return result.reverse().toString();

    }

    public static int findDecimalFromExcelColumn(String columnName) {
        StringBuilder input = new StringBuilder(columnName);
        input.reverse();
        int number = 0;
        int multiplier = 1;
        for( int i=0; i< input.length(); i++ ) {
            int digit = (input.charAt(i) - 'A' +1);
            multiplier *= (i==0)?1:26;
            number += digit * multiplier ;
        }

       return number;
    }


    public static void main(String[] args) {
        System.out.println(findExcelColumn(705));
        System.out.println(findDecimalFromExcelColumn("AAC"));
    }
}
