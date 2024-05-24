package com.example.geektrust.service;

import com.example.geektrust.common.GeneralValues;
import com.example.geektrust.common.RoundValuesGenerator;
import com.example.geektrust.exception.CalculateBillException;
import com.example.geektrust.model.AllRides;
import com.example.geektrust.model.Rider;
import com.example.geektrust.model.Rides;

import java.text.DecimalFormat;

public class Bill {
    private final User user;
    private final AllRides allRides;

    public Bill(User user, AllRides allRides){
        this.user = user;
        this.allRides = allRides;
    }

    public void calculateBill(String rideId){
        try{
            Rides ride = allRides.getRide(rideId);
            if(ride == null){
                System.out.print("INVALID_RIDE\n");
            }
            else{
                double finalBill = getFinalBill(user.getRider(ride.getRiderId()), ride, allRides.getRide(rideId).getTimeTaken());
                System.out.println("BILL " + rideId + " " + ride.getDriverId() + " " + new DecimalFormat("#.00").format(finalBill));
            }
        }
        catch (Exception e){
            throw new CalculateBillException("Error while calculating bill");
        }
    }

    private double getFinalBill(Rider rider, Rides ride, int timeTaken) {
        double billWithoutST = getBillWithoutST(rider, ride, timeTaken);
        double serviceTaxAmount = RoundValuesGenerator.roundValue((billWithoutST * GeneralValues.serviceTax)/100);
        return billWithoutST + serviceTaxAmount;
    }

    private double getBillWithoutST(Rider rider, Rides ride, int timeTaken){
        return RoundValuesGenerator.roundValue(GeneralValues.baseFare
                + (GeneralValues.perKilometer * calculateRiderToDestinationDistance(rider, ride))
                + (GeneralValues.perMinute * timeTaken));
    }

    private double calculateRiderToDestinationDistance(Rider rider, Rides ride){
        return RoundValuesGenerator.roundValue(Math.sqrt(Math.pow((rider.getCoordinateX() - ride.getCoordinateX()), GeneralValues.power) +
                Math.pow((rider.getCoordinateY() - ride.getCoordinateY()), GeneralValues.power)));
    }
}
