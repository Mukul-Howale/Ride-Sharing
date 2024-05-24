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
    private final User user;

    public MatchMaking(User user){
        this.user = user;
    }

    public void match(String riderID){
        try{
            Rider rider = user.getRider(riderID);
            List<Match> matches = getMatches(rider);
            if(matches.isEmpty()) {
                System.out.print("NO_DRIVERS_AVAILABLE\n");
            }
            else{
                rider.addMatches(matches);
                printDriversMatched(matches);
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
            if(matches.size() >= GeneralValues.maxDrivers) break;
            Driver driver = mapElement.getValue();
            double driverToRiderDistance = calculateDriverToRiderDistance(driver, rider);
            if(driverToRiderDistance <= GeneralValues.maxDistance && !driver.isNowRiding())
                matches.add(new Match(driver.getDriverId(), driverToRiderDistance));
        }
        return matches;
    }

    private double calculateDriverToRiderDistance(Driver driver, Rider rider){
        return RoundValuesGenerator.roundValue(Math.sqrt(Math.pow((driver.getCoordinateX() - rider.getCoordinateX()), GeneralValues.power) +
                Math.pow((driver.getCoordinateY() - rider.getCoordinateY()), GeneralValues.power)));
    }

    private void printDriversMatched(List<Match> matches){
        StringBuilder matchedDrivers = new StringBuilder();
        matches.forEach(match -> matchedDrivers.append(match.getDriverId()).append(" "));
        System.out.println("DRIVERS_MATCHED " + matchedDrivers);
    }
}
