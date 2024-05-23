package com.example.geektrust.service;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.geektrust.GeneralValues;
import com.example.geektrust.exception.CalculateBillException;
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
    private static Map<String, Rides> rides;

    public Ride(){
        drivers = new HashMap<>();
        riders = new HashMap<>();
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
            Rider rider = riders.get(riderID);
            List<Match> matches = getMatches(rider);
            if(matches.isEmpty()) {
                System.out.println("NO_DRIVERS_AVAILABLE");
            }
            else{
                rider.addMatches(matches);
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
            List<Match> matches = riders.get(riderId).getMatches();
            if(matches.isEmpty() || matches.size() < nthDriver || rides.getOrDefault(rideId, null) != null) {
                System.out.println("INVALID_RIDE");
            }
            else{
                Driver driver = drivers.get(matches.get(nthDriver-1).getDriverId());
                driver.setStartRiding();
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
                drivers.get(ride.getDriverId()).setStopRiding();
                ride.setAfterRideValues(coordinateX,coordinateY,timeTaken);
                System.out.println("RIDE_STOPPED " + rideId);
            }
        }
        catch (Exception e){
            throw new StopRideFailedException("Error while stopping a ride");
        }
    }

    public void calculateBill(String rideId){
        try{
            Rides ride = rides.getOrDefault(rideId, null);
            if(ride == null){
                System.out.println("INVALID_RIDE");
            }
            else{
                double finalBill = getFinalBill(riders.get(ride.getRiderId()), ride, rides.get(rideId).getTimeTaken());
                System.out.println("BILL " + rideId + " " + ride.getDriverId() + " " + new DecimalFormat("#.00").format(finalBill));
            }
        }
        catch (Exception e){
            throw new CalculateBillException("Error while calculating bill");
        }
    }

    // Private classes

    private List<Match> getMatches(Rider rider){
        List<Match> matches = rider.getMatches();
        for (Map.Entry<String, Driver> mapElement : drivers.entrySet()) {
            Driver driver = mapElement.getValue();
            if(matches.size() >= GeneralValues.maxDrivers) break;
            if(driver.isNowRiding()) continue;
            double driverToRiderDistance = calculateDriverToRiderDistance(driver, rider);
            if(driverToRiderDistance <= GeneralValues.maxDistance) matches.add(new Match(driver.getDriverId(), driverToRiderDistance));
        }
        return matches;
    }

    private double getFinalBill(Rider rider, Rides ride, int timeTaken) {
        double billWithoutST = roundValue(GeneralValues.baseFare
                + (GeneralValues.perKilometer * calculateRiderToDestinationDistance(rider, ride))
                + (GeneralValues.perMinute * timeTaken));
        double serviceTaxAmount = roundValue((billWithoutST * GeneralValues.serviceTax)/100);
        return billWithoutST + serviceTaxAmount;
    }

    private double calculateDriverToRiderDistance(Driver driver, Rider rider){
        return roundValue(Math.sqrt(Math.pow((driver.getCoordinateX() - rider.getCoordinateX()), 2) +
                Math.pow((driver.getCoordinateY() - rider.getCoordinateY()), 2)));
    }

    private double calculateRiderToDestinationDistance(Rider rider, Rides ride){
        return roundValue(Math.sqrt(Math.pow((rider.getCoordinateX() - ride.getCoordinateX()), 2) +
                Math.pow((rider.getCoordinateY() - ride.getCoordinateY()), 2)));
    }

    private static double roundValue(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
