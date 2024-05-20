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

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }

    public boolean isRiding() {
        return isRiding;
    }

    public void setRiding(boolean riding) {
        isRiding = riding;
    }

    public String getRideId() {
        return rideId;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getRiderId() {
        return riderId;
    }
}
