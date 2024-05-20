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

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }
}
