package com.example.geektrust.exception;

public class MatchMakingFailedException extends RuntimeException{
    public MatchMakingFailedException(String message){
        super(message);
    }
}
