package com.interview.string;

/**
 * Implement a function OneEditApart with the following signature:
 * bool OneEditApart(string s1, string s2)
 *
 * OneEditApart("cat", "dog") = false
 * OneEditApart("cat", "cats") = true
 * OneEditApart("cat", "cut") = true
 * OneEditApart("cat", "cast") = true
 * OneEditApart("cat", "at") = true
 * OneEditApart("cat", "acts") = false
 * Edit is: insertion, removal, replacement
 *
 * http://www.careercup.com/question?id=4793416529477632
 * Created by abhimaloo on 8/7/14.
 */
public class OneEditApart {
    public static String s1 = "cat";
    public static String s2 = "bat";

    public static boolean oneEditApart(String s1, String s2) {
        //try finding out which is smaller or bigger length string
        String smaller = s1.length() <= s2.length()?s1 :s2;
        String larger = s1.length() > s2.length()?s1 :s2;

        //if difference in length is bigger than 1 then return false
        if(larger.length()- smaller.length() >1) {
            return false;
        } else if(smaller.length() == larger.length()){ // if length are same
            //compare each index and count mismatch in a variable
            int count =0;
            for( int i=0; i< smaller.length(); i++){
                if(larger.charAt(i) != smaller.charAt(i)){
                    count++;
                }
                //if number of mismatch is more than 1 .. return false
                if(count >1){
                    return false;
                }
            }

        } else {
            //if one is smaller than other
            int count =0;
            //iterate on smaller one
            for( int i=0; i< smaller.length(); i++){
                // using the count variable to compare bigger with smaller one
                if(larger.charAt(i+count) != smaller.charAt(i)){
                    // increment the mistake
                    count++;
                    //this is because larger string could be one letter more and we should give
                    //the same of index of smaller string a chance to get compared to the bigger string's next index
                    i--;
                }
                //if more than one difference return false
                if(count >1){
                    return false;
                }
            }

        }

        // if nothing happens return true;
        return true;

    }

    public static void main(String[] args) {
        System.out.println(oneEditApart(s1, s2));
    }

}
