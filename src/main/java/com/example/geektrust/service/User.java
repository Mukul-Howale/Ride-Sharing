package com.example.geektrust.service;

import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Rider;

import java.util.HashMap;
import java.util.Map;

public class User {
    private static Map<String, Driver> drivers;
    private static Map<String, Rider> riders;

    public User(){
        drivers = new HashMap<>();
        riders = new HashMap<>();
    }

    public void addDriver(String driverID, int coordinateX, int coordinateY){
        drivers.put(driverID, new Driver(driverID, coordinateX, coordinateY));
    }

    public void addRider(String riderID, int coordinateX, int coordinateY){
        riders.put(riderID, new Rider(riderID, coordinateX, coordinateY));
    }

    public Map<String, Driver> getAllDrivers(){
        return drivers;
    }

    public Driver getDriver(String driverId){
        return drivers.get(driverId);
    }

    public Rider getRider(String riderId){
        return riders.get(riderId);
    }
}
