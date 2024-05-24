package com.example.geektrust.service;

import com.example.geektrust.exception.StartRideFailedException;
import com.example.geektrust.exception.StopRideFailedException;
import com.example.geektrust.model.AllRides;
import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Match;
import com.example.geektrust.model.Rides;

import java.util.*;

public class Riding {
    private final User user;
    private final AllRides allRides;

    public Riding(User user, AllRides allRides){
        this.allRides = allRides;
        this.user = user;
    }

    public void startRiding(String rideId, int nthDriver, String riderId){
        try{
            List<Match> matches = user.getRider(riderId).getMatches();
            if(matches.isEmpty() || matches.size() < nthDriver || allRides.getRide(rideId) != null) {
                System.out.println("INVALID_RIDE");
            }
            else{
                Driver driver = user.getDriver(matches.get(nthDriver-1).getDriverId());
                driver.setStartRiding();
                addRides(rideId, new Rides(driver.getDriverId(), riderId), "RIDE_STARTED ");
            }
        }
        catch (Exception e){
            throw new StartRideFailedException("Error while starting a ride");
        }
    }

    public void stopRiding(String rideId, int coordinateX, int coordinateY, int timeTaken){
        try{
            Rides ride = allRides.getRide(rideId);
            if(ride == null || !ride.isRiding()){
                System.out.print("INVALID_RIDE\n");
            }
            else{
                user.getDriver(ride.getDriverId()).setStopRiding();
                ride.setAfterRideValues(coordinateX,coordinateY,timeTaken);
                addRides(rideId, ride, "RIDE_STOPPED ");
            }
        }
        catch (Exception e){
            throw new StopRideFailedException("Error while stopping a ride");
        }
    }

    private void addRides(String rideId, Rides ride, String rideString){
        allRides.addRide(rideId, ride);
        System.out.println(rideString + rideId);
    }
}
