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


    public static String countAndSay2(int n) {
        if (n<1){
            return null;
        }
        int i=2;
        String current="1";
        while (i<=n){
            current=say(current);
            i++;
        }

        return current;
    }

    // count each char in given input string
    private static String say(String input){
        char last=input.charAt(0);

        String result="";

        int i=1;// index

        int count=1;// count for each char
        while (i<input.length()){
            if (input.charAt(i)==last){
                count++;
            }
            else{
                result+=count;
                result+=last;

                last=input.charAt(i);
                count=1;

            }

            i++;

        }

        result+=count;
        result+=last;

        return result;

    }





    public static void main(String[] args) {
        //System.out.println(count(12));
        System.out.println(countAndSay(7));
        System.out.println(countAndSay2(7));
    }
}
