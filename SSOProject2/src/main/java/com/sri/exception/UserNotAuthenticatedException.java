package com.sri.exception;

public class UserNotAuthenticatedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotAuthenticatedException() {
        super();
    }

    public UserNotAuthenticatedException(String message) {
        super(message);
    }
}