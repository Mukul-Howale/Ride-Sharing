package com.example.geektrust.exception;

public class StartRideFailedException extends RuntimeException{
    public StartRideFailedException(String message){
        super(message);
    }
}
