package com.example.geektrust.model;

public class Rider {
    private final String riderId;
    private int coordinateX;
    private int coordinateY;

    public Rider(String riderId, int coordinateX, int coordinateY){
        this.riderId = riderId;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public String getRiderId() {
        return riderId;
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
