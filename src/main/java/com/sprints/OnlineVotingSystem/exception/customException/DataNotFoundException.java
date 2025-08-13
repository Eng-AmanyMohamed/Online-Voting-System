package com.sprints.OnlineVotingSystem.exception.customException;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
