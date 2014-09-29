package com.facebook;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class CountAndSay {
    public static String count(String n) {

        String result = "";

        for(int i = 0; i< n.length(); i++) {
            int count = 1;
            int j = i+1;
            while(j < n.length() && n.charAt(j) == n.charAt(i)) {
                count++;
                j++;
            }
            if(j!= i+1){
               i = j-1;
            }
            result += count + ""+ n.charAt(i);

        }

        return result;
    }

    public static String countAndSay(int n) {
       if(n < 1) {
           return null;
       }
       String seq = "1";
       for( int i = 1; i< n; i++) {
           seq = count(seq);
       }

       return seq;
    }




    public static void main(String[] args) {
        //System.out.println(count(12));
        System.out.println(countAndSay(7));
    }
}
