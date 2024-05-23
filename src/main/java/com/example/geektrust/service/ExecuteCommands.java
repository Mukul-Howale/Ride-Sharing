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

    public ExecuteCommands(){
        user = new User();
        matchMaking = new MatchMaking(user);
        riding = new Riding(user, new Rides());
        bill = new Bill(user, new Rides());
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
            user.addDriver(command[1],
                    Integer.parseInt(command[2]),
                    Integer.parseInt(command[3]));
        } else if (command[0].equals(Commands.ADD_RIDER.toString())) {
            user.addRider(command[1],
                    Integer.parseInt(command[2]),
                    Integer.parseInt(command[3]));
        } else if (command[0].equals(Commands.MATCH.toString())) {
            matchMaking.match(command[1]);
        } else if (command[0].equals(Commands.START_RIDE.toString())) {
            riding.startRiding(command[1],
                    Integer.parseInt(command[2]),
                    command[3]);
        } else if (command[0].equals(Commands.STOP_RIDE.toString())) {
            riding.stopRiding(command[1],
                    Integer.parseInt(command[2]),
                    Integer.parseInt(command[3]),
                    Integer.parseInt(command[4]));
        } else if (command[0].equals(Commands.BILL.toString())) {
            bill.calculateBill(command[1]);
        }
    }
}
