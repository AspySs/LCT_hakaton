package com.hack.app.exceptions;

public class IdeaNotFoundException extends RuntimeException {
    public IdeaNotFoundException(String message) {
        super(message);
    }
}
