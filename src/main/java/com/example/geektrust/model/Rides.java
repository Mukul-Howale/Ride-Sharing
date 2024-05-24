package com.example.geektrust.model;

import java.util.HashMap;
import java.util.Map;

public class Rides {
    private String driverId;
    private String riderId;
    private int coordinateX;
    private int coordinateY;
    private int timeTaken;
    private boolean isRiding;

    public Rides(String driverId, String riderId){
        this.driverId = driverId;
        this.riderId = riderId;
        this.coordinateX = 0;
        this.coordinateY = 0;
        this.timeTaken = 0;
        this.isRiding = true;
    }

    public void setAfterRideValues(int coordinateX, int coordinateY, int timeTaken){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.timeTaken = timeTaken;
        this.isRiding = false;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public boolean isRiding() {
        return isRiding;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getRiderId() {
        return riderId;
    }
}
