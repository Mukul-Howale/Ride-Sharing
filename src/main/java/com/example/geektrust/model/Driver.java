package com.example.geektrust.model;

public class Driver {
    private final String driverId;
    private int coordinateX;
    private int coordinateY;

    public Driver(String driverId, int coordinateX, int coordinateY){
        this.driverId = driverId;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public String getDriverId() {
        return driverId;
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
}
