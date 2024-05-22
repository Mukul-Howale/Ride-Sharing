package com.example.geektrust.model;

public class Driver {
    private final String driverId;
    private final int coordinateX;
    private final int coordinateY;
    private boolean nowRiding;

    public Driver(String driverId, int coordinateX, int coordinateY){
        this.driverId = driverId;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.nowRiding = false;
    }

    public boolean isNowRiding() {
        return nowRiding;
    }

    public void setNowRiding(boolean nowRiding) {
        this.nowRiding = nowRiding;
    }

    public String getDriverId() {
        return driverId;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }
}
