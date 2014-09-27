package com.leetcode;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

 You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

 Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.


 * Created by abhimaloo on 8/25/14.
 */
public class GasStation {
    public static int[] gas = {4,4,4,4,7};
    public static int[] cost = {3,5,2,6,4};

    /**
     * trick is  -
     * iterate through all the gas stations twice in loop try to calculate the gas left at every station
     * if gas left at any station is negative than that station cannot be start point. we have to start from the next station and check if thats valid
     * after lopping if gas is still left and we approached the start station return it else return -1;
     * @param gas
     * @param cost
     * @return
     */
    public static int findStartingStation(int[] gas, int[] cost) {


        int N = gas.length;
        int gasLeft = 0;
        int startStation = 0;

        // we are looping twice so that if last station(gas.length() -1)th is start point,
        // we should give him a chance to demonstrate a circuit as well.
        for (int i = 0; i < 2 * N; i++) {
            int k = i % N;
            // at every station calculate tha aggregated sum of gas - cost
            gasLeft += gas[k] - cost[k];
            // gas left goes negative
            if (gasLeft < 0) {
                // start station has to be the next one
                startStation = (i + 1) % N;
                // reset the gas left for newer loop starting from i+1
                gasLeft = 0;
            }
            else {
                // if gas left is positive and we are on the station before the start one .. we can confirm that start station is correct
                if (startStation == (i + 1) % N)
                    return startStation;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        System.out.println(findStartingStation(gas, cost));
    }
}
