package com.interview.graphs;

import java.util.*;

/**
 * Created by abhishekm787 on 8/22/14.
 */
public class MazeWithHurdles {
    public static int [][] maze = {{0,0,0,1,0,0 },
                                   {0,0,1,0,0,0 },
                                   {0,0,1,0,1,0},
                                   {0,0,1,0,1,0},
                                   {0,0,1,0,1,0},
                                   {0,0,0,0,1,0}};
    // this represents a coordinate x,y and has equals and hashcode implemented
    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = hash*31 + this.x;
            hash = hash*31 + this.y;
            return hash;
        }

        @Override
        public boolean equals(Object b){
            Pair p = (Pair) b;
            return p.x == this.x && p.y == this.y;
        }


    }

    /**
     * Implementing BFS based solution
    */
    public static void findWay(int[][] maze, Pair start, Pair end) {
        LinkedList<Pair> queue = new LinkedList<>();
        Map<Pair, Pair> parent = new HashMap<>();


        queue.addLast(start);
        parent.put(start, null);

        while(!queue.isEmpty()) {
            Pair p = queue.removeFirst();
            if(p.equals(end)) {
                break;
            }

            // find all the neighbours who are are not walls or previously visited
            for(Pair neighbour : getAllNeighbour(p, maze)) {
                if(!parent.containsKey(neighbour)){
                    queue.addLast(neighbour);
                    parent.put(neighbour, p);
                }
            }
        }

        //follow the parent pointer
        if(parent.containsKey(end)) {
            maze[end.x][end.y] = 3;
            while(true) {
                if(parent.get(end) == null) {
                    break;
                }
                maze[parent.get(end).x][parent.get(end).y] = 3;
                end = parent.get(end);
            }
        }
        printBoard(maze);
    }

    private static List<Pair> getAllNeighbour(Pair source, int [][] maze) {
        List<Pair> neighbours = new ArrayList<>();
        if(source.y - 1 >= 0 && maze[source.x][source.y-1] != 1 ) {
           neighbours.add(new Pair(source.x, source.y-1));
        }
        if(source.x - 1 >= 0 && maze[source.x-1][source.y] != 1 ) {
            neighbours.add(new Pair(source.x-1, source.y));
        }
        if(source.y + 1 < maze.length && maze[source.x][source.y+1] != 1) {
            neighbours.add(new Pair(source.x, source.y+1));
        }
        if(source.x + 1 < maze.length && maze[source.x+1][source.y] != 1) {
            neighbours.add(new Pair(source.x+1, source.y));
        }
        return neighbours;
    }

    public static void main(String[] args) {
        findWay(MazeWithHurdles.maze, new Pair(0,0), new Pair(5,5));
    }

    public static void printBoard(int [][] board) {

        for(int i =0; i<maze.length; i++) {
            for(int j=0; j<maze.length; j++) {
                System.out.print(" "+board[i][j] + " ");
            }
            System.out.println(" ");
        }

        System.out.println("------------------------");
    }


}
