package com.sprints.OnlineVotingSystem.exception.customException;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String message) {
        super(message);
    }
}
