package com.sprints.OnlineVotingSystem.exception.customException;

public class UneligibleVoterException extends RuntimeException {
    public UneligibleVoterException(String message) {
        super(message);
    }
}
