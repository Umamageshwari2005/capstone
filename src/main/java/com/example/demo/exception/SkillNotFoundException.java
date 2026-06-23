package com.example.demo.exception;

public class SkillNotFoundException extends RuntimeException {

    public SkillNotFoundException(String message) {
        super(message);
    }
}