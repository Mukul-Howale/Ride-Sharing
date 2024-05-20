package com.example.geektrust;

import com.example.geektrust.exception.StopRideCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExecuteCommands {

    private String[] command;
    private static Ride ride;

    ExecuteCommands(){
        command = null;
        ride = new Ride();
    }

    public void processCommands(List<String> getAllLines){
        readCommands(getAllLines);
    }

    private void readCommands(List<String> getAllLines){
        try {
            boolean checkStopRideCommand = false;
            for (String eachLines : getAllLines) {
                command = eachLines.trim().split(" ");
                if(command[0].equals(Commands.STOP_RIDE.toString())) checkStopRideCommand = true;
                executeCommands(command);
            }
            if(!checkStopRideCommand){
                throw new StopRideCommandException("RIDE_NOT_COMPLETED");
            }
        }
        catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private void executeCommands(String[] command){
        try {
            if(command[0].equals(Commands.ADD_DRIVER.toString())){
                String driverID = command[1];
                List<Integer> driverCoordinates = new ArrayList<>();
                driverCoordinates.add(0, Integer.parseInt(command[2]));
                driverCoordinates.add(1, Integer.parseInt(command[3]));
                ride.addDriver(driverID, driverCoordinates);
            }
            else if(command[0].equals(Commands.ADD_RIDER.toString())){
                String riderID = command[1];
                List<Integer> riderCoordinates = new ArrayList<>();
                riderCoordinates.add(0, Integer.parseInt(command[2]));
                riderCoordinates.add(1, Integer.parseInt(command[3]));
                ride.addRider(riderID, riderCoordinates);
            }
            else if(command[0].equals(Commands.MATCH.toString())){
                ride.match(command[1]);
            }
            else if(command[0].equals(Commands.START_RIDE.toString())){

            }
            else if(command[0].equals(Commands.STOP_RIDE.toString())){

            }
            else if(command[0].equals(Commands.BILL.toString())){

            }
        }
        catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
