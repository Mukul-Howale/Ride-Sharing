package com.example.geektrust.service;

import com.example.geektrust.Commands;

import java.util.List;

public class ExecuteCommands {

    private static Ride ride;

    public ExecuteCommands(){
        ride = new Ride();
    }

    public void processCommands(List<String> getAllLines){
        readCommands(getAllLines);
    }

    private void readCommands(List<String> getAllLines){

        boolean checkStopRideCommand = false;
        for (String eachLines : getAllLines) {
            String[] command = eachLines.trim().split(" ");
            if(command[0].equals(Commands.STOP_RIDE.toString())) checkStopRideCommand = true;
            executeCommands(command);
        }
        if(!checkStopRideCommand){
            System.out.println("RIDE_NOT_COMPLETED");
        }
    }

    private void executeCommands(String[] command){
        if(command[0].equals(Commands.ADD_DRIVER.toString())){
            String driverID = command[1];
            int coordinateX = Integer.parseInt(command[2]);
            int coordinateY = Integer.parseInt(command[3]);
            ride.addDriver(driverID, coordinateX, coordinateY);
        }
        else if(command[0].equals(Commands.ADD_RIDER.toString())){
            String riderID = command[1];
            int coordinateX = Integer.parseInt(command[2]);
            int coordinateY = Integer.parseInt(command[3]);
            ride.addRider(riderID, coordinateX, coordinateY);
        }
        else if(command[0].equals(Commands.MATCH.toString())){
            ride.match(command[1]);
        }
        else if(command[0].equals(Commands.START_RIDE.toString())){
            String rideId = command[1];
            int nthDriver = Integer.parseInt(command[2]);
            String riderId = command[3];
            ride.startRide(rideId,nthDriver,riderId);
        }
        else if(command[0].equals(Commands.STOP_RIDE.toString())){
            String rideId = command[1];
            int coordinateX = Integer.parseInt(command[2]);
            int coordinateY = Integer.parseInt(command[3]);
            int timeTaken = Integer.parseInt(command[4]);
            ride.stopRide(rideId,coordinateX,coordinateY,timeTaken);
        }
        else if(command[0].equals(Commands.BILL.toString())){
            String rideId = command[1];
            ride.calculateBill(rideId);
        }
    }
}
