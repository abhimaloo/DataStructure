package com.interview.design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * Design a parking lot
 * Created by abhimaloo on 9/5/14.
 */
public class ParkingLot {
    // this keeps list of free parking space available
    public LinkedList<ParkingSpace> freeParkingSpace = new LinkedList<>() ;
    // this keeps the map of parking space already allotted
    public Map<String, ParkingSpace> filledParkingSpace = new HashMap<>();


    public ParkingLot(int maxParking) {
        // add the free parking spaces into parking lot
        for( int i=0; i< maxParking; i++) {
            freeParkingSpace.add(new ParkingSpace(i, null));
        }
    }

    /**
     * this method simulated a car coming into parking lot
     * we park the car and give a receipt to the driver
     * @param car
     * @return
     */
    public Receipt checkin(Car car) {
        // check if we have parking available
        if(!freeParkingSpace.isEmpty()) {
            // find the first available parking space
            ParkingSpace space = freeParkingSpace.removeFirst();
            // park the car
            space.car = car;
            // update our filled parking lot map with this new space
            filledParkingSpace.put(car.registration, space);
            // return a receipt
            return new Receipt(space.number, car.registration);
        } else {
            return null;
        }
    }


    public Car checkout(Receipt receipt) {

        Car car = null;
        // find the space from filled parking area by looking at receipt's car licence plate
        ParkingSpace space = filledParkingSpace.get(receipt.registration);
        //take out the car
        car = space.car;
        //vacate the parking space
        space.car = null;
        //remove this space from filled parking area
        filledParkingSpace.remove(receipt.registration);
        // add this space as available parking space
        freeParkingSpace.addLast(space);
        return car;
    }



    static class ParkingSpace {
        int number;
        Car car;

        ParkingSpace(int number, Car car) {
            this.number = number;
            this.car = car;
        }
    }


    static class Receipt {
        public int parkingSpace;
        public String registration;

        Receipt(int parkingSpace, String registration) {
            this.parkingSpace = parkingSpace;
            this.registration = registration;
        }
    }

    static class Car {
         String registration;
        CarType type;

        Car(String registration, CarType type) {
            this.registration = registration;
            this.type = type;
        }
    }

    static enum CarType {
        COMPACT, FULL_SIZE;
    }

    static enum LotType {
        FULL_SIZE,COMPACT,HANDICAPPED;
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(10);

        Receipt r1 = lot.checkin(new Car("1", CarType.COMPACT));
        Receipt r2 = lot.checkin(new Car("2", CarType.COMPACT));
        Receipt r3 = lot.checkin(new Car("3", CarType.COMPACT));

        System.out.println(lot.checkout(r2).registration);


    }
}
