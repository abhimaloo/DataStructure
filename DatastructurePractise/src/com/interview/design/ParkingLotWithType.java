package com.interview.design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by abhimaloo on 9/5/14.
 */
public class ParkingLotWithType {

    public LinkedList<ParkingSpace> freeHandicappedParkingSpace = new LinkedList<>() ;
    public Map<String, ParkingSpace> filledHandicappedParkingSpace = new HashMap<>() ;

    public LinkedList<ParkingSpace> freeCompactParkingSpace = new LinkedList<>() ;
    public Map<String, ParkingSpace> filledCompactParkingSpace = new HashMap<>() ;

    public LinkedList<ParkingSpace> freeFullSizeParkingSpace = new LinkedList<>() ;
    public Map<String, ParkingSpace> filledFullSizeParkingSpace = new HashMap<>() ;


    public ParkingLotWithType(int maxHandicapped, int maxCompact, int maxFullSize) {
        for( int i=0; i< maxHandicapped; i++) {
            freeHandicappedParkingSpace.add(new ParkingSpace(i, null,LotType.HANDICAPPED));
        }

        for( int i=0; i< maxCompact; i++) {
            freeCompactParkingSpace.add(new ParkingSpace(i, null,LotType.COMPACT));
        }

        for( int i=0; i< maxFullSize; i++) {
            freeFullSizeParkingSpace.add(new ParkingSpace(i, null,LotType.FULL_SIZE));
        }
    }


    public Receipt checkin(Car car, boolean isHandicapped) {

        if(isHandicapped) {
            if(!freeHandicappedParkingSpace.isEmpty()) {
                ParkingSpace space = freeHandicappedParkingSpace.removeFirst();
                space.car = car;
                filledHandicappedParkingSpace.put(car.registration, space);
                return new Receipt(space.number, car.registration, LotType.HANDICAPPED);
            }
        }

        if(car.type == CarType.COMPACT) {
            if(!freeCompactParkingSpace.isEmpty()) {
                ParkingSpace space = freeCompactParkingSpace.removeFirst();
                space.car = car;
                filledCompactParkingSpace.put(car.registration, space);
                return new Receipt(space.number, car.registration, LotType.COMPACT);
            } else {
                return null;
            }
        }

        if(car.type == CarType.FULL_SIZE) {
            if(!freeFullSizeParkingSpace.isEmpty()) {
                ParkingSpace space = freeFullSizeParkingSpace.removeFirst();
                space.car = car;
                filledFullSizeParkingSpace.put(car.registration, space);
                return new Receipt(space.number, car.registration, LotType.FULL_SIZE);
            } else {
                return null;
            }
        }

        return null;

    }


    public Car checkout(Receipt receipt) {
        Car car  =  null;
        ParkingSpace space = null;
        switch (receipt.lotType) {
            case HANDICAPPED:
                space = filledHandicappedParkingSpace.get(receipt.registration);
                car = space.car;
                space.car = null;
                filledHandicappedParkingSpace.remove(space);
                freeHandicappedParkingSpace.addLast(space);

                break;
            case COMPACT:
                space = filledCompactParkingSpace.get(receipt.registration);
                car = space.car;
                space.car = null;
                filledCompactParkingSpace.remove(space);
                freeCompactParkingSpace.addLast(space);

                break;
            case FULL_SIZE:
                space = filledFullSizeParkingSpace.get(receipt.registration);
                car = space.car;
                space.car = null;
                filledFullSizeParkingSpace.remove(space);
                freeFullSizeParkingSpace.addLast(space);
                break;
        }

        return car;
    }



    static class ParkingSpace {
        int number;
        Car car;
        LotType type;

        ParkingSpace(int number, Car car, LotType type) {
            this.number = number;
            this.car = car;
            this.type = type;
        }
    }


    static class Receipt {
        public int parkingSpace;
        public String registration;
        public LotType lotType;

        Receipt(int parkingSpace, String registration, LotType lotType) {
            this.parkingSpace = parkingSpace;
            this.registration = registration;
            this.lotType = lotType;
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

}
