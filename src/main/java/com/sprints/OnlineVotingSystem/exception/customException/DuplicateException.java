package com.sprints.OnlineVotingSystem.exception.customException;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }
}
