package com.example.geektrust.service;

import com.example.geektrust.common.Commands;
import com.example.geektrust.exception.ExecuteCommandsException;
import com.example.geektrust.model.Rides;

import java.util.List;

public class ExecuteCommands {

    private static Riding riding;
    private static User user;
    private static MatchMaking matchMaking;
    private static Bill bill;
    private static Rides rides;

    public ExecuteCommands(){
        user = new User();
        matchMaking = new MatchMaking(user);
        rides = new Rides();
        riding = new Riding(user, rides);
        bill = new Bill(user, rides);
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

    private void executeCommands(String[] command) {
        if (command[0].equals(Commands.ADD_DRIVER.toString())) {
            String driverID = command[1];
            int coordinateX = Integer.parseInt(command[2]);
            int coordinateY = Integer.parseInt(command[3]);
            user.addDriver(driverID, coordinateX, coordinateY);
        } else if (command[0].equals(Commands.ADD_RIDER.toString())) {
            String riderID = command[1];
            int coordinateX = Integer.parseInt(command[2]);
            int coordinateY = Integer.parseInt(command[3]);
            user.addRider(riderID, coordinateX, coordinateY);
        } else if (command[0].equals(Commands.MATCH.toString())) {
            matchMaking.match(command[1]);
        } else if (command[0].equals(Commands.START_RIDE.toString())) {
            String rideId = command[1];
            int nthDriver = Integer.parseInt(command[2]);
            String riderId = command[3];
            riding.startRiding(rideId, nthDriver, riderId);
        } else if (command[0].equals(Commands.STOP_RIDE.toString())) {
            String rideId = command[1];
            int coordinateX = Integer.parseInt(command[2]);
            int coordinateY = Integer.parseInt(command[3]);
            int timeTaken = Integer.parseInt(command[4]);
            riding.stopRiding(rideId, coordinateX, coordinateY, timeTaken);
        } else if (command[0].equals(Commands.BILL.toString())) {
            String rideId = command[1];
            bill.calculateBill(rideId);
        }
    }
}
