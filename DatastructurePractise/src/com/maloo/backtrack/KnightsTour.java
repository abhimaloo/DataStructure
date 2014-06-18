package com.maloo.backtrack;

/*
* This Problem is called Knight's Tour .
* It entails moving a knight on a chess board so that it covers every square of chess board
* without repeating any of the visited squares
* */

public class KnightsTour {


    public static int[][] board= {{0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0}
                                 ,{0,0,0,0,0,0,0,0}
                                 ,{0,0,0,0,0,0,0,0}
                                 ,{0,0,0,0,0,0,0,0}
                                 ,{0,0,0,0,0,0,0,0}
                                 ,{0,0,0,0,0,0,0,0}
                                 ,{0,0,0,0,0,0,0,0}};

    public static Point[] findPossibleMoves(Point currentPoint, int [][] board) {
        Point[] points = new Point[8];
        int currIndex = 0;
        // Option one Top Right move
        if(currentPoint.x+1 <= 7 && currentPoint.y -2 >=0 && board[currentPoint.x+1][currentPoint.y -2] == 0 ) {
            Point point = new Point(currentPoint.x +1,currentPoint.y -2);
            points[currIndex++] = point;
        }

        // Option two Top left move
        if(currentPoint.x-1 >= 0 && currentPoint.y -2 >=0 && board[currentPoint.x-1][currentPoint.y -2] == 0) {
            Point point = new Point(currentPoint.x -1,currentPoint.y -2);
            points[currIndex++] = point;
        }

        // Option three bottom Right move
        if(currentPoint.x+1 <= 7 && currentPoint.y +2 <=7  && board[currentPoint.x+1][currentPoint.y +2] == 0) {
            Point point = new Point(currentPoint.x +1,currentPoint.y +2);
            points[currIndex++] = point;
        }

        // Option four bottom left move
        if(currentPoint.x-1 >= 0 && currentPoint.y +2 <=7 && board[currentPoint.x-1][currentPoint.y +2] == 0 ) {
            Point point = new Point(currentPoint.x -1, currentPoint.y +2);
            points[currIndex++] = point;
        }

        // Option five left bottom move
        if(currentPoint.x-2 >= 0 && currentPoint.y +1 <=7 && board[currentPoint.x-2][currentPoint.y +1] == 0) {
            Point point = new Point(currentPoint.x -2, currentPoint.y +1);
            points[currIndex++] = point;
        }

        // Option six  left top move
        if(currentPoint.x-2 >= 0 && currentPoint.y -1 >=0 && board[currentPoint.x-2][currentPoint.y -1] == 0) {
            Point point = new Point(currentPoint.x -2, currentPoint.y -1);
            points[currIndex++] = point;
        }

        // Option seven right bottom move
        if(currentPoint.x+2 <= 7 && currentPoint.y +1 <=7 && board[currentPoint.x+2][currentPoint.y+1] == 0) {
            Point point = new Point(currentPoint.x +2,currentPoint.y +1 );
            points[currIndex++] = point;
        }

        // Option eight  right top move
        if(currentPoint.x+2 <= 7 && currentPoint.y -1 >=0 && board[currentPoint.x+2][currentPoint.y -1] == 0) {
            Point point = new Point(currentPoint.x +2, currentPoint.y -1);
            points[currIndex++] = point;
        }

        return points;

    }



    public static boolean tour(int [][] board, int lastStep, Point p ){

        //check if tour is over
        if(isTourOver(board)){
            return true;
        }

        //find next possible moves
        Point[] possibleNextMoves = findPossibleMoves(p,board);

        //iterate through each move and try finding whether one of them reaches the end state or not
        for(int i =0; i < possibleNextMoves.length; i++) {
            if(possibleNextMoves[i] != null){

                //record the next move
                lastStep = recordMove(board,possibleNextMoves[i],lastStep);

                //recurse on the next move .. return true if it reaches the solution
                if(tour(board,lastStep,possibleNextMoves[i])){
                    return true;
                } else {
                    //back track
                    lastStep = unrecordMove(board,possibleNextMoves[i],lastStep);
                }
            }

        }

        return false;
    }

    public static int recordMove(int[][] board, Point p, int lastStep) {
        board[p.x][p.y] = ++lastStep;
        System.out.println("----Recording Move : "+lastStep+" ----");
        printBoard(board);

        return lastStep;
    }

    public static int unrecordMove(int[][] board, Point p, int lastStep) {
        System.out.println("----UnRecording Move : "+lastStep+"----");
        board[p.x][p.y] = 0;
        lastStep--;

        printBoard(board);
        return  lastStep;
    }

    public static boolean isTourOver(int [][] board) {
        for(int i =0; i<8; i++) {
             for(int j=0; j<8; j++) {
                 if(board[i][j] == 0){
                     return false;
                 }
             }
        }
        return true;
    }

    public static void printBoard(int [][] board) {
        for(int i =0; i<8; i++) {
            for(int j=0; j<8; j++) {
                System.out.print(" "+board[i][j] + " ");
            }
            System.out.println(" ");
        }
    }


   public static void main(String args[]) {
       int[][] board= {{1,0,0,0,0,0,0,0},
               {0,0,0,0,0,0,0,0}
               ,{0,0,0,0,0,0,0,0}
               ,{0,0,0,0,0,0,0,0}
               ,{0,0,0,0,0,0,0,0}
               ,{0,0,0,0,0,0,0,0}
               ,{0,0,0,0,0,0,0,0}
               ,{0,0,0,0,0,0,0,0}};

       tour(board,1,new Point(0,0));
       printBoard(board);


   }

}

class Point {
    public int x;
    public  int y;
    public Point(int x, int y) {
        this.x = x ;
        this.y = y;
    }
}
