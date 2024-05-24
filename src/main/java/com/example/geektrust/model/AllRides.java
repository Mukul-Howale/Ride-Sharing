package com.example.geektrust.model;

import java.util.HashMap;
import java.util.Map;

public class AllRides {
    private final Map<String, Rides> allRides;

    public AllRides(){
        allRides = new HashMap<>();
    }

    public void addRide(String rideId, Rides rides){
        allRides.put(rideId,rides);
    }

    public Rides getRide(String rideId){
        return allRides.get(rideId);
    }

}
