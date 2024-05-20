package com.example.geektrust.exception;

public class InvalidRideException extends RuntimeException{
    public InvalidRideException(String message){
        super(message);
    }
}
