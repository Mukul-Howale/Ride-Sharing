package com.example.geektrust.service;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Match;
import com.example.geektrust.model.Rider;

import java.util.*;

public class Ride {
    private static Map<String, Driver> drivers;
    private static Map<String, Rider> riders;
    private static Map<String, List<Match>> allMatches;
    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    Ride(){
        drivers = new HashMap<>();
        riders = new HashMap<>();
        allMatches = new HashMap<>();
    }

    public void addDriver(String driverID, int coordinateX, int coordinateY){
        drivers.put(driverID, new Driver(driverID, coordinateX, coordinateY));
    }

    public void addRider(String riderID, int coordinateX, int coordinateY){
        riders.put(riderID, new Rider(riderID, coordinateX, coordinateY));
    }

    public void match(String riderID){
        int noOfRiders = drivers.size();
        int count = 0;
        Rider rider = riders.get(riderID);
        List<Match> matches = new ArrayList<>();
        decfor.setRoundingMode(RoundingMode.DOWN);
        for (Map.Entry<String, Driver> mapElement : drivers.entrySet()) {
            if(count == noOfRiders || count >= 5) break;
            Driver driver = mapElement.getValue();
            double distance = Math.sqrt(Math.pow((driver.getCoordinateX() - rider.getCoordinateX()), 2) +
                    Math.pow((driver.getCoordinateY() - rider.getCoordinateY()), 2));
            matches.add(new Match(driver.getDriverId(), Double.parseDouble(decfor.format(distance))));
            count++;
        }
        matches.sort(Comparator.comparing(Match::getDistance));
        allMatches.put(riderID, matches);
    }

    public void startRide(String rideId, int nthDriver, String riderId){

    }
}
