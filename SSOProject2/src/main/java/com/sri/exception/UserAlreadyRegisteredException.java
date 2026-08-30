package com.sri.exception;

public class UserAlreadyRegisteredException
        extends RuntimeException {

	/**
	     * 
	     */
	    private static final long serialVersionUID = 1L;
	public UserAlreadyRegisteredException() {
		super();
	}
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}