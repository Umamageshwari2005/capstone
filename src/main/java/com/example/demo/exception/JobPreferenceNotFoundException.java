package com.example.demo.exception;

public class JobPreferenceNotFoundException extends RuntimeException {

    public JobPreferenceNotFoundException(String message) {
        super(message);
    }
}