package com.carwash.washer_service.exception;

public class WasherNotFoundException extends RuntimeException {
    public WasherNotFoundException(String message) {
        super(message);
    }
}