package com.sprints.OnlineVotingSystem.exception.customException;

public class ElectionNotActiveException extends RuntimeException {
    public ElectionNotActiveException(String message) {
        super(message);
    }
}
