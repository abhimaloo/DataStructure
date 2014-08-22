package com.interview.graphs;

import java.util.*;



/**
 * Created by abhishekm787 on 8/22/14.
 */
public class MazeWithHurdleAStar {

    static class Pw {
        Pair p ;
        int weight;

        Pw(Pair p, int weight) {
            this.p = p;
            this.weight = weight;
        }
    }

    static class Pair {
        int x;
        int y;
        int weight;

        Pair(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
            if(this.weight == 0) {
                this.weight = 1;
            }

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

    public static int[][] maze =
           {{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {0,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1},
            {1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1},
            {1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,1},
            {1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1},
            {1,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,1},
            {1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1},
            {1,0,1,0,1,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1},
            {1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,1},
            {1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,1},
            {1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1},
            {1,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,1,0,1},
            {1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1},
            {1,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,1},
            {1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1},
            {1,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1},
            {1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,1},
            {1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,1},
            {1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,0,1},
            {1,0,0,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,1},
            {1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1}};

    /**
     * A star Algorithm
     * Its a mixture of Dijkstra and BFS with little heuristics
     * like dijkstra maintain a min heap of Vertex and its priority as weight
     * like dijkstra maintain the cost Map and Parent Map
     *
     *
     *
     *
     * @param maze
     * @param start
     * @param end
     */

    public static void findWay(int[][] maze, Pair start, Pair end) {
        // Min Heap holding
        PriorityQueue<Pw> heap = new PriorityQueue<>(maze.length * maze.length , new Comparator<Pw>() {
            @Override
            public int compare(Pw o1, Pw o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });

        Map<Pair, Pair> parent = new HashMap<>();
        Map<Pair, Integer> cost = new HashMap<>();

        heap.add(new Pw(start, 0));
        parent.put(start, null);
        cost.put(start, 0);

        while(!heap.isEmpty()) {
            Pw pairWeight = heap.remove();

            if(pairWeight.p.equals(end)){
                break;
            }

            for(Pair neighbour : getAllNeighbour(pairWeight.p, maze)) {
                int newCost = cost.get(pairWeight.p) + neighbour.weight;
                if(!parent.containsKey(neighbour) && (!cost.containsKey(neighbour) || newCost < cost.get(neighbour))) {
                   cost.put(neighbour, newCost);
                   int weight = newCost + Math.abs(end.x - neighbour.x) + Math.abs(end.y - neighbour.y);
                   heap.add(new Pw(neighbour,weight));
                    parent.put(neighbour, pairWeight.p);
                }
            }

        }

        if(parent.containsKey(end)) {
            maze[end.x][end.y] = 2;
            while(true) {
                if(parent.get(end) == null) {
                    break;
                }
                maze[parent.get(end).x][parent.get(end).y] = 2;
                end = parent.get(end);
            }
        }
        printBoard(maze);


    }

    private static List<Pair> getAllNeighbour(Pair source, int [][] maze) {
        List<Pair> neighbours = new ArrayList<>();
        if(source.y - 1 >= 0 && maze[source.x][source.y-1] != 1 ) {
            neighbours.add(new Pair(source.x, source.y-1, maze[source.x][source.y-1]));
        }
        if(source.x - 1 >= 0 && maze[source.x-1][source.y] != 1 ) {
            neighbours.add(new Pair(source.x-1, source.y,maze[source.x-1][source.y]));
        }
        if(source.y + 1 < maze[0].length && maze[source.x][ source.y+1] != 1) {
            neighbours.add(new Pair(source.x, source.y+1, maze[source.x][ source.y+1]));
        }
        if(source.x + 1 < maze.length && maze[source.x+1][source.y] != 1) {
            neighbours.add(new Pair(source.x+1, source.y, maze[source.x+1][source.y]));
        }
        return neighbours;
    }

    public static void printBoard(int [][] board) {

        for(int i =0; i<maze.length; i++) {
            for(int j=0; j<maze[0].length; j++) {
                System.out.print(" "+board[i][j] + " ");
            }
            System.out.println(" ");
        }

        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        MazeWithHurdleAStar.findWay(maze, new Pair(1,0,0), new Pair(maze.length-1, maze[0].length -2,0));
    }
}
