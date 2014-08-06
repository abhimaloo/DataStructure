package com.interview.string;

/**
 * Given a keypad as shown in diagram, and a n digit number, list all words which are possible by pressing these numbers.
 For example if input number is 234, possible words which can be formed are (Alphabetical order):
 adg adh adi aeg aeh aei afg afh afi bdg bdh bdi beg beh bei bfg bfh bfi cdg cdh cdi ceg ceh cei cfg cfh cfi

 http://www.geeksforgeeks.org/find-possible-words-phone-digits/
 * Created by abhishekm787 on 8/5/14.
 */
public class PrintAllPossibleWordsFromPhoneDigit {
    public static String phoneDigit  = "123";

    /**
     * trick is to use backtracking algorithm
     * @param phoneDigit
     * @param index
     * @param message
     */
    public static void  printAllPossibleWords(String phoneDigit, int index, String message) {

        //find all the possible letters which can be made by using letter at index
        String[] possibleLetters = findPossibleMapForIndex(phoneDigit.charAt(index));
        //iterate through all possible letters
        for( int i = 0 ;i <possibleLetters.length; i++) {
            //add one of the letter to message
            //standard backtracking ..record the selection
            message += possibleLetters[i];
            //recurse till the length of the string
            if(index+1<phoneDigit.length()){
                printAllPossibleWords(phoneDigit,index+1, message);
            } else {
                //while popping out print the message
                //do the processing ..backtracking phase
                System.out.println(message);
            }
            //this check is for empty string
            //remove the last letter which was just added to the message. Standard backtracking approach ..unrecording the selection
            message = message.length()>0?message.substring(0,message.length()-1): message;

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
        printAllPossibleWords("234", 0,"");
    }
}
