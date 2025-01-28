package com.planner.application.exceptions;

public class TripInvalidDateException extends RuntimeException {
    public TripInvalidDateException(String message) {
        super(message);
    }
}
