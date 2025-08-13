package com.sprints.OnlineVotingSystem.exception.customException;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
