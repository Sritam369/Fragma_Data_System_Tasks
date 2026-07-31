package com.sri.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CSVFileException.class)
	public ResponseEntity<ExceptionDetails> handleCsvException(CSVFileException e){
		
		ExceptionDetails ed = new ExceptionDetails(LocalDateTime.now(),e.getMessage(),"CSV file not found in the resource");
		
		return new ResponseEntity<ExceptionDetails>(ed,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDetails> handleException(Exception e) {

	    ExceptionDetails details = new ExceptionDetails(
	            LocalDateTime.now(),
	            e.getMessage(),
	            "Something went wrong."
	    );

	    return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
