package com.maloo.geet;


import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by abhimaloo on 3/19/15.
 */
public class LetterGrader {
    private Map<String, StudentGrade> students = null;
    private String inputFileName;
    private String outputFileName;

    public LetterGrader(String inputFileName, String outputFileName) {
        this.students = new TreeMap<>();
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public void readScore() throws Exception {
        BufferedReader bufferReader = null;
        InputStream in = getClass().getResourceAsStream(inputFileName);
        try {
            //Instantiate the BufferedReader Class
            bufferReader = new BufferedReader(new InputStreamReader(in));

            //Variable to hold the one line data
            String line;

            // Read file line by line and print on the console
            while ((line = bufferReader.readLine()) != null)   {
                String[] parts = line.split(",");
                StudentGrade student = new StudentGrade(
                        parts[0].trim(),
                        Integer.parseInt(parts[1].trim()),
                        Integer.parseInt(parts[2].trim()),
                        Integer.parseInt(parts[3].trim()),
                        Integer.parseInt(parts[4].trim()),
                        Integer.parseInt(parts[5].trim()),
                        Integer.parseInt(parts[6].trim()),
                        Integer.parseInt(parts[7].trim())
                );
                students.put(student.getStudentName(), student);
            }

        } catch(Exception e){
            System.out.println("Error while reading file line by line:" + e.getMessage());
            throw e;
        } finally {
            bufferReader.close();
            in.close();
        }

    }

    public void calcLetterGrade() {
        for(String student : students.keySet()) {
            students.get(student).calculateGrade();
        }
    }

    public void printGrade() throws Exception {

        BufferedWriter bw = null;
        FileOutputStream out = new FileOutputStream(new File(outputFileName));
        try {
            bw = new BufferedWriter(new OutputStreamWriter(out));
            for(String studentName : students.keySet()) {
                String grade =  students.get(studentName).getGrade();
                bw.write(studentName + "    " + grade);
                bw.newLine();
            }
        } catch(Exception e) {
            System.out.println("Error while writing to the output file");
            throw e;
        } finally {
            bw.close();
            out.close();
        }

    }

    public void displayAverage() {

        System.out.println("\t\tQ1\t\tQ2\t\tQ3\t\tQ4\t\tMid1\t\tMid2\t\tFinal");
        int sumQ1 = 0; int minQ1 = 100; int maxQ1 = 0;
        int sumQ2 = 0; int minQ2 = 100; int maxQ2 = 0;
        int sumQ3 = 0; int minQ3 = 100; int maxQ3 = 0;
        int sumQ4 = 0; int minQ4 = 100; int maxQ4 = 0;
        int sumMid1 = 0; int minMid1 = 100; int maxMid1 = 0;
        int sumMid2 = 0; int minMid2 = 100; int maxMid2 = 0;
        int sumFinal = 0; int minFinal = 100; int maxFinal = 0;

        for(String studentName : students.keySet()) {
            StudentGrade studentGrade = students.get(studentName);
            sumQ1 += studentGrade.getQuiz1();
            if(minQ1 > studentGrade.getQuiz1()) {
                minQ1 = studentGrade.getQuiz1();
            }
            if(maxQ1 < studentGrade.getQuiz1()) {
                maxQ1 = studentGrade.getQuiz1();
            }

            sumQ2 += studentGrade.getQuiz2();
            if(minQ2 > studentGrade.getQuiz2()) {
                minQ2 = studentGrade.getQuiz2();
            }
            if(maxQ2 < studentGrade.getQuiz2()) {
                maxQ2 = studentGrade.getQuiz2();
            }

            sumQ3 += studentGrade.getQuiz3();
            if(minQ3 > studentGrade.getQuiz3()) {
                minQ3 = studentGrade.getQuiz3();
            }
            if(maxQ3 < studentGrade.getQuiz3()) {
                maxQ3 = studentGrade.getQuiz3();
            }

            sumQ4 += studentGrade.getQuiz4();
            if(minQ4 > studentGrade.getQuiz4()) {
                minQ4 = studentGrade.getQuiz4();
            }
            if(maxQ4 < studentGrade.getQuiz4()) {
                maxQ4 = studentGrade.getQuiz4();
            }

            sumMid1 += studentGrade.getMidTerm1();
            if(minMid1 > studentGrade.getMidTerm1()) {
                minMid1 = studentGrade.getMidTerm1();
            }
            if(maxMid1 < studentGrade.getMidTerm1()) {
                maxMid1 = studentGrade.getMidTerm1();
            }

            sumMid2 += studentGrade.getMidTerm2();
            if(minMid2 > studentGrade.getMidTerm2()) {
                minMid2 = studentGrade.getMidTerm2();
            }
            if(maxMid2 < studentGrade.getMidTerm2()) {
                maxMid2 = studentGrade.getMidTerm2();
            }

            sumFinal += studentGrade.getFinalTerm();
            if(minFinal > studentGrade.getFinalTerm()) {
                minFinal = studentGrade.getFinalTerm();
            }
            if(maxFinal < studentGrade.getFinalTerm()) {
                maxFinal = studentGrade.getFinalTerm();
            }
        }

        System.out.print("\nAverage\t");
        System.out.print((sumQ1 / (double)students.size()) + "\t");
        System.out.print(sumQ2 / (double)students.size() + "\t");
        System.out.print(sumQ3 / (double)students.size() + "\t");
        System.out.print(sumQ4 / (double)students.size() + "\t");
        System.out.print(sumMid1 / (double)students.size() + "\t");
        System.out.print(sumMid2 / (double)students.size() + "\t");
        System.out.print(sumFinal / (double)students.size() + "\t");

        System.out.print("\nMinimum\t");
        System.out.print(minQ1 + "\t");
        System.out.print(minQ2 + "\t");
        System.out.print(minQ3 + "\t");
        System.out.print(minQ4 + "\t");
        System.out.print(minMid1 + "\t");
        System.out.print(minMid2 + "\t");
        System.out.print(minFinal + "\t");

        System.out.print("\nMaximum\t");
        System.out.print(maxQ1 + "\t");
        System.out.print(maxQ2 + "\t");
        System.out.print(maxQ3 + "\t");
        System.out.print(maxQ4 + "\t");
        System.out.print(maxMid1 + "\t");
        System.out.print(maxMid2 + "\t");
        System.out.print(maxFinal + "\t");

    }






}
