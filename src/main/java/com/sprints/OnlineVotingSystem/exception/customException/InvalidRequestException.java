package com.sprints.OnlineVotingSystem.exception.customException;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
