package com.example.geektrust.model;

public class Match {
    private final String driverId;
    private double distance;

    public Match(String driverId, double distance){
        this.driverId = driverId;
        this.distance = distance;
    }

    public String getDriverId() {
        return driverId;
    }

    public double getDistance() {
        return distance;
    }
}
