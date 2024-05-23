package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Rider {
    private final String riderId;
    private final int coordinateX;
    private final int coordinateY;
    private List<Match> matches;

    public Rider(String riderId, int coordinateX, int coordinateY){
        this.riderId = riderId;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.matches = new ArrayList<>();
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void addMatches(List<Match> matches){
        matches.sort(Comparator.comparing(Match::getDistance).thenComparing(Match::getDriverId));
        this.matches = matches;
    }

    public List<Match> getMatches(){
        return matches;
    }
}
