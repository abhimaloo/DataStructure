package com.maloo.geet;

/**
 * Created by abhimaloo on 3/19/15.
 */
public class TestLetterGrader {

    public static void main(String[] args) throws Exception {

        if(args.length < 2) {
            throw new RuntimeException("Please pass proper arguments");
        }

        LetterGrader letterGrader = new LetterGrader(args[0], args[1]);
        letterGrader.readScore();
        letterGrader.calcLetterGrade();
        letterGrader.printGrade();
        letterGrader.displayAverage();
    }
}
