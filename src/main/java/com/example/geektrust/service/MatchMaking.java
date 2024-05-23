package com.example.geektrust.service;

import com.example.geektrust.common.GeneralValues;
import com.example.geektrust.common.RoundValuesGenerator;
import com.example.geektrust.exception.MatchMakingFailedException;
import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Match;
import com.example.geektrust.model.Rider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MatchMaking {
    User user;

    public MatchMaking(User user){
        this.user = user;
    }

    public void match(String riderID){
        try{
            Rider rider = user.getRider(riderID);
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

    private List<Match> getMatches(Rider rider){
        List<Match> matches = new ArrayList<>();
        Map<String, Driver> allDrivers = user.getAllDrivers();
        for (Map.Entry<String, Driver> mapElement : allDrivers.entrySet()) {
            Driver driver = mapElement.getValue();
            if(matches.size() >= GeneralValues.maxDrivers) break;
            if(driver.isNowRiding()) continue;
            double driverToRiderDistance = calculateDriverToRiderDistance(driver, rider);
            if(driverToRiderDistance <= GeneralValues.maxDistance) matches.add(new Match(driver.getDriverId(), driverToRiderDistance));
        }
        return matches;
    }

    private double calculateDriverToRiderDistance(Driver driver, Rider rider){
        return RoundValuesGenerator.roundValue(Math.sqrt(Math.pow((driver.getCoordinateX() - rider.getCoordinateX()), 2) +
                Math.pow((driver.getCoordinateY() - rider.getCoordinateY()), 2)));
    }
}
