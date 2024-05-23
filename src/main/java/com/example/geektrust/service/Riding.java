package com.example.geektrust.service;

import com.example.geektrust.exception.StartRideFailedException;
import com.example.geektrust.exception.StopRideFailedException;
import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Match;
import com.example.geektrust.model.Rides;

import java.util.*;

public class Riding {
    private final User user;
    private final Rides rides;

    public Riding(User user, Rides rides){
        this.rides = rides;
        this.user = user;
    }

    public void startRiding(String rideId, int nthDriver, String riderId){
        try{
            List<Match> matches = user.getRider(riderId).getMatches();
            if(matches.isEmpty() || matches.size() < nthDriver || rides.getRide(rideId) != null) {
                System.out.println("INVALID_RIDE");
            }
            else{
                Driver driver = user.getDriver(matches.get(nthDriver-1).getDriverId());
                driver.setStartRiding();
                rides.setBeforeRideValues(driver.getDriverId(), riderId);
                rides.addRide(rideId, rides);
                System.out.println("RIDE_STARTED " + rideId);
            }
        }
        catch (Exception e){
            throw new StartRideFailedException("Error while starting a ride");
        }
    }

    public void stopRiding(String rideId, int coordinateX, int coordinateY, int timeTaken){
        try{
            Rides ride = rides.getRide(rideId);
            if(ride == null || !ride.isRiding()){
                System.out.println("INVALID_RIDE");
            }
            else{
                user.getDriver(ride.getDriverId()).setStopRiding();
                ride.setAfterRideValues(coordinateX,coordinateY,timeTaken);
                System.out.println("RIDE_STOPPED " + rideId);
            }
        }
        catch (Exception e){
            throw new StopRideFailedException("Error while stopping a ride");
        }
    }
}
