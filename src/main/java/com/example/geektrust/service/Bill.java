package com.example.geektrust.service;

import com.example.geektrust.common.GeneralValues;
import com.example.geektrust.common.RoundValuesGenerator;
import com.example.geektrust.exception.CalculateBillException;
import com.example.geektrust.model.Rider;
import com.example.geektrust.model.Rides;

import java.text.DecimalFormat;

public class Bill {
    private final User user;
    private final Rides rides;

    public Bill(User user, Rides rides){
        this.user = user;
        this.rides = rides;
    }

    public void calculateBill(String rideId){
        try{
            Rides ride = rides.getRide(rideId);
            if(ride == null){
                System.out.println("INVALID_RIDE");
            }
            else{
                double finalBill = getFinalBill(user.getRider(ride.getRiderId()), ride, rides.getRide(rideId).getTimeTaken());
                System.out.println("BILL " + rideId + " " + ride.getDriverId() + " " + new DecimalFormat("#.00").format(finalBill));
            }
        }
        catch (Exception e){
            throw new CalculateBillException("Error while calculating bill");
        }
    }

    private double getFinalBill(Rider rider, Rides ride, int timeTaken) {
        double billWithoutST = RoundValuesGenerator.roundValue(GeneralValues.baseFare
                + (GeneralValues.perKilometer * calculateRiderToDestinationDistance(rider, ride))
                + (GeneralValues.perMinute * timeTaken));
        double serviceTaxAmount = RoundValuesGenerator.roundValue((billWithoutST * GeneralValues.serviceTax)/100);
        return billWithoutST + serviceTaxAmount;
    }

    private double calculateRiderToDestinationDistance(Rider rider, Rides ride){
        return RoundValuesGenerator.roundValue(Math.sqrt(Math.pow((rider.getCoordinateX() - ride.getCoordinateX()), 2) +
                Math.pow((rider.getCoordinateY() - ride.getCoordinateY()), 2)));
    }
}
