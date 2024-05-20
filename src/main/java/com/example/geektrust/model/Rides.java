package com.example.geektrust.model;

public class Rides {
    private final String rideId;
    private final String driverId;
    private final String riderId;
    private int coordinateX;
    private int coordinateY;
    private int timeTaken;
    private boolean isRiding;

    public Rides(String rideId, String driverId, String riderId){
        this.rideId = rideId;
        this.driverId = driverId;
        this.riderId = riderId;
        coordinateX = 0;
        coordinateY = 0;
        timeTaken = 0;
        isRiding = true;
    }

    public void setAfterRideValues(int coordinateX, int coordinateY, int timeTaken){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.timeTaken = timeTaken;
        isRiding = false;
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
