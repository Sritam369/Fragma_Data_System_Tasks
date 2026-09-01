package com.sri.exception;

public class ConstituencyNotFoundException extends RuntimeException {

	public ConstituencyNotFoundException() {
		super();
	}
	
	public ConstituencyNotFoundException(String msg) {
		super(msg);
	}
}
