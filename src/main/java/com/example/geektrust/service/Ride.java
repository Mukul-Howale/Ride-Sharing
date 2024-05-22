package com.example.geektrust.service;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.geektrust.GeneralValues;
import com.example.geektrust.exception.MatchMakingFailedException;
import com.example.geektrust.exception.StartRideFailedException;
import com.example.geektrust.exception.StopRideFailedException;
import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Match;
import com.example.geektrust.model.Rider;
import com.example.geektrust.model.Rides;

import java.text.DecimalFormat;
import java.util.*;

public class Ride {
    private static Map<String, Driver> drivers;
    private static Map<String, Rider> riders;
    private static Map<String, List<Match>> allMatches;
    private static Map<String, Rides> rides;

    public Ride(){
        drivers = new HashMap<>();
        riders = new HashMap<>();
        allMatches = new HashMap<>();
        rides = new HashMap<>();
    }

    public void addDriver(String driverID, int coordinateX, int coordinateY){
        drivers.put(driverID, new Driver(driverID, coordinateX, coordinateY));
    }

    public void addRider(String riderID, int coordinateX, int coordinateY){
        riders.put(riderID, new Rider(riderID, coordinateX, coordinateY));
    }

    public void match(String riderID){
        try{
            int noOfDrivers = drivers.size();
            int count = 0;
            Rider rider = riders.get(riderID);
            List<Match> matches = new ArrayList<>();
            for (Map.Entry<String, Driver> mapElement : drivers.entrySet()) {
                Driver driver = mapElement.getValue();
                if(count == noOfDrivers || matches.size() >= GeneralValues.maxDrivers) break;
                if(driver.isNowRiding()) continue;
                double distance = roundValue(Math.sqrt(Math.pow((driver.getCoordinateX() - rider.getCoordinateX()), 2) +
                        Math.pow((driver.getCoordinateY() - rider.getCoordinateY()), 2)));
                if(distance <= GeneralValues.maxDistance) matches.add(new Match(driver.getDriverId(), distance));
            }
            if(matches.isEmpty()) {
                System.out.println("NO_DRIVERS_AVAILABLE");
            }
            else{
                matches.sort(Comparator.comparing(Match::getDistance).thenComparing(Match::getDriverId));
                allMatches.put(riderID, matches);
                StringBuilder matchedDrivers = new StringBuilder();
                matches.forEach(match -> matchedDrivers.append(match.getDriverId()).append(" "));
                System.out.println("DRIVERS_MATCHED " + matchedDrivers);
            }
        }
        catch (Exception e){
            throw new MatchMakingFailedException("Error while matching drivers");
        }
    }

    public void startRide(String rideId, int nthDriver, String riderId){
        try{
            List<Match> matches = allMatches.getOrDefault(riderId, null);
            Rides ride = rides.getOrDefault(rideId, null);
            if(matches == null || matches.size() < nthDriver || ride != null) {
                System.out.println("INVALID_RIDE");
            }
            else{
                Driver driver = drivers.get(matches.get(nthDriver-1).getDriverId());
                driver.setNowRiding(true);
                rides.put(rideId, new Rides(rideId, driver.getDriverId(), riderId));
                System.out.println("RIDE_STARTED " + rideId);
            }
        }
        catch (Exception e){
            throw new StartRideFailedException("Error while starting a ride");
        }
    }

    public void stopRide(String rideId, int coordinateX, int coordinateY, int timeTaken){
        try{
            Rides ride = rides.getOrDefault(rideId, null);
            if(ride == null || !ride.isRiding()){
                System.out.println("INVALID_RIDE");
            }
            else{
                Driver driver = drivers.get(ride.getDriverId());
                driver.setNowRiding(false);
                ride.setAfterRideValues(coordinateX,coordinateY,timeTaken);
                System.out.println("RIDE_STOPPED " + rideId);
            }
        }
        catch (Exception e){
            throw new StopRideFailedException("Error while stopping a ride");
        }
    }

    public void calculateBill(String rideId){
        Rides ride = rides.getOrDefault(rideId, null);
        if(ride == null){
            System.out.println("INVALID_RIDE");
        }
        else{
            Rider rider = riders.get(ride.getRiderId());
            int timeTaken = rides.get(rideId).getTimeTaken();
            double finalBill = getFinalBill(rider, ride, timeTaken);
            System.out.println("BILL " + rideId + " " + ride.getDriverId() + " " + new DecimalFormat("#.00").format(finalBill));
        }
    }

    private double getFinalBill(Rider rider, Rides ride, int timeTaken) {
        double riderToDestinationDistance = roundValue(Math.sqrt(Math.pow((rider.getCoordinateX() - ride.getCoordinateX()), 2) +
                Math.pow((rider.getCoordinateY() - ride.getCoordinateY()), 2)));
        double billWithoutST = roundValue(GeneralValues.baseFare + (GeneralValues.perKilometer * riderToDestinationDistance)
                + (GeneralValues.perMinute * timeTaken));
        double serviceTaxAmount = roundValue((billWithoutST * GeneralValues.serviceTax)/100);
        return billWithoutST + serviceTaxAmount;
    }

    private static double roundValue(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
