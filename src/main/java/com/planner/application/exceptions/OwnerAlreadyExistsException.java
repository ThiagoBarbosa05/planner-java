package com.planner.application.exceptions;

public class OwnerAlreadyExistsException extends RuntimeException{
    public OwnerAlreadyExistsException(String message) {
        super(message);
    }
}
