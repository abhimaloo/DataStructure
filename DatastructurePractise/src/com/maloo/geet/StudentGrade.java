package com.maloo.geet;

/**
 * Created by abhimaloo on 3/19/15.
 */
public class StudentGrade {
    private String studentName;
    private int quiz1;
    private int quiz2;
    private int quiz3;
    private int quiz4;
    private int midTerm1;
    private int midTerm2;
    private int finalTerm;

    private String grade;

    public StudentGrade(String studentName, int quiz1, int quiz2, int quiz3, int quiz4, int midTerm1, int midTerm2, int finalTerm) {
        this.studentName = studentName;
        this.quiz1 = quiz1;
        this.quiz2 = quiz2;
        this.quiz3 = quiz3;
        this.quiz4 = quiz4;
        this.midTerm1 = midTerm1;
        this.midTerm2 = midTerm2;
        this.finalTerm = finalTerm;
    }

    public String calculateGrade() {
        double totalMarks = (quiz1 + quiz2 + quiz3 + quiz4) * .10;
        totalMarks += (midTerm1) * .20;
        totalMarks += (midTerm2) * .15;
        totalMarks += finalTerm * .25;

        double percentage = totalMarks; //because total maximum marks are 100 hence totalMarks would be equal to the percentage
        if(percentage >= 90) {
            grade = "A";
        } else if(percentage >=80 && percentage <= 89) {
            grade = "B";
        } else if(percentage >=70 && percentage <= 79) {
            grade = "C";
        } else if(percentage >=60 && percentage <= 69) {
            grade = "D";
        } else {
            grade = "F";
        }

        return grade;

    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getQuiz1() {
        return quiz1;
    }

    public void setQuiz1(int quiz1) {
        this.quiz1 = quiz1;
    }

    public int getQuiz2() {
        return quiz2;
    }

    public void setQuiz2(int quiz2) {
        this.quiz2 = quiz2;
    }

    public int getQuiz3() {
        return quiz3;
    }

    public void setQuiz3(int quiz3) {
        this.quiz3 = quiz3;
    }

    public int getQuiz4() {
        return quiz4;
    }

    public void setQuiz4(int quiz4) {
        this.quiz4 = quiz4;
    }

    public int getMidTerm1() {
        return midTerm1;
    }

    public void setMidTerm1(int midTerm1) {
        this.midTerm1 = midTerm1;
    }

    public int getMidTerm2() {
        return midTerm2;
    }

    public void setMidTerm2(int midTerm2) {
        this.midTerm2 = midTerm2;
    }

    public int getFinalTerm() {
        return finalTerm;
    }

    public void setFinalTerm(int finalTerm) {
        this.finalTerm = finalTerm;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
