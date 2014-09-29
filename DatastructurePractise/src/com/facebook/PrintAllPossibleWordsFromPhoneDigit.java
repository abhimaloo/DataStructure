package com.facebook;

/**
 * /**
 * Given a keypad as shown in diagram, and a n digit number, list all words which are possible by pressing these numbers.
 For example if input number is 234, possible words which can be formed are (Alphabetical order):
 adg adh adi aeg aeh aei afg afh afi bdg bdh bdi beg beh bei bfg bfh bfi cdg cdh cdi ceg ceh cei cfg cfh cfi

 http://www.geeksforgeeks.org/find-possible-words-phone-digits/
 * Created by abhishekm787 on 8/5/14.
 */

public class PrintAllPossibleWordsFromPhoneDigit {

    public static void printAllPossibleWords(String digits, int index, String message) {
        if(digits == null || digits == ""){
            return;
        }

        if(index == digits.length()) {
            System.out.println(message);
            return;
        }


        String[] possibleChars = findPossibleMapForIndex(digits.charAt(index));
        if(possibleChars != null && possibleChars.length >0) {
            for( int i = 0; i<possibleChars.length; i++) {
                message += possibleChars[i];
                printAllPossibleWords(digits, index+1, message);
                // backtrack
                message = message.substring(0, message.length() -1);
            }
        }
    }

    private static String[] findPossibleMapForIndex(char c) {
        if(c == '0' || c =='1'){
            return new String[] {""};
        }  else{
            switch (c) {
                case '2' : return new String[] {"a", "b", "c"};
                case '3' : return new String[] {"d","e","f"};
                case '4' : return new String[] {"g","h","i"};
                case '5' : return new String[] {"j","k","l"};
                case '6' : return new String[] {"m","n","o"};
                case '7' : return new String[] {"p","q","r","s"};
                case '8' : return new String[] {"t","u","v"};
                case '9' : return new String[] {"w","x","y","z"};

            }

        }

        return null;
    }

    public static void main(String[] args) {
        printAllPossibleWords("23", 0,"");
    }

}
