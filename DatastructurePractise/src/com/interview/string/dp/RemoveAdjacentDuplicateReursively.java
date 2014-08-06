package com.interview.string.dp;

/**
 * Given a string, recursively remove adjacent duplicate characters from string.
 * The output string should not have any adjacent duplicates.
 *
 * Input:  azxxzy
 * Output: First "azxxzy" is reduced to "azzy". The string "azzy" contains duplicates,
 so it is further reduced to "ay"
 *
 * Created by abhishekm787 on 8/5/14.
 */
public class RemoveAdjacentDuplicateReursively {
    public static String input = "acaaabbbacdddd";

    /**
     * trick is to find all the even sized palindromes and then delete them from the original string
     * @param input
     * @return
     */
    public static String removeAdjancentRecursively(String input) {
       char[] mask = input.toCharArray();
        boolean palindrome[][] = new boolean[input.length()][input.length()];

        //set single letter palindromes
        for( int i=0; i< input.length(); i++) {
            palindrome[i][i] = true;
        }

        //set double letter palindromes
        for( int i=1; i< input.length(); i++) {
            if(input.charAt(i-1) == input.charAt(i)){
                palindrome[i-1][i] = true;
                //maintaining a mask where we store null if we detect even sized palindromes in the system
                mask[i-1] = '\0';
                mask[i] = '\0';
            }
        }

        //set 3 to n letter palindrome
        for( int k =3; k<= input.length(); k++){
            for( int i=0; i< input.length()-k+1; i++) {
                int j = i+k-1;
                if( input.charAt(i) == input.charAt(j) && palindrome[i+1][j-1]){
                    palindrome[i][j] = true;
                    //if the length of palindrome is mark palindromic elements as '\0'
                    if((j-i)%2==0){
                        int x = i;
                        for(;x<=j;x++){
                            mask[x] = '\0';
                        }
                    }

                }
            }
        }

        StringBuilder result = new StringBuilder();
        //separating nulls and returning the result
        for(int i=0; i< mask.length; i++){
            if(mask[i]!='\0'){
                result.append(mask[i]);
            }
        }
         return result.toString();
    }

    public static String removeRecursively(String s) {
        java.util.LinkedList<Character> stack = new java.util.LinkedList<>();

        boolean matched = false;

        for( int i=0; i<s.length(); i++){
            if(stack.isEmpty()){
                stack.push(s.charAt(i));
                matched = false;
            } else {
                if(!matched) {
                    if(stack.peek() == s.charAt(i)){
                        matched = true;
                    } else {
                        stack.push(s.charAt(i));
                    }
                } else {
                    if(stack.peek() != s.charAt(i)){
                       stack.pop();
                        matched = false;
                    }
                }

            }
        }

        if(stack.isEmpty()) {
            return "-1";
        } else {
            StringBuilder builder = new StringBuilder();
            while(!stack.isEmpty()){
                 builder.append(stack.pop());
            }
            return builder.reverse().toString();
        }


    }

    public static String removeDuplicates(String s) {
        if (s.isEmpty()) {
            return s;
        }
        char[] buf = s.toCharArray();
        char lastchar = buf[0];

        // i: index of input char
        // o: index of output char
        int o = 1;
        for (int i = 1; i < buf.length; i++) {
            if (o > 0 && buf[i] == buf[o - 1]) {
                lastchar = buf[o - 1];
                while (o > 0 && buf[o - 1] == lastchar) {
                    o--;
                }
            } else if (buf[i] == lastchar) {
                // Don't copy to output
            } else {
                buf[o++] = buf[i];
            }
        }
        return new String(buf, 0, o);
    }



    public static void main(String[] args) {
        //System.out.println(removeAdjancentRecursively(input));
        System.out.println(removeDuplicates(input));
    }
}
