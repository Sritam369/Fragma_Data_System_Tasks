package com.sri.exception;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status", 400,
                        "error", "Bad Request",
                        "message", ex.getMessage()
                ));
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidRequestBody(HttpMessageNotReadableException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status", 400,
                        "error", "Bad Request",
                        "message", "Invalid request body"
                ));
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(Map.of(
                        "status", 405,
                        "error", "Method Not Allowed",
                        "message", "HTTP method not supported"
                ));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {

        ex.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "status", 500,
                        "error", "Internal Server Error",
                        "message", "Something went wrong on the server"
                ));
    }
    
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDatabaseException(DataAccessException ex) {

        ex.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "status", 500,
                        "error", "Database Error",
                        "message", "Unable to process database request"
                ));
    }
    
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<?> handleUserAlreadyRegistered(UserAlreadyRegisteredException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "status", 409,
                        "error", "Conflict",
                        "message", ex.getMessage()
                ));
    }
    
    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<?> handleUserNotAuthenticated(UserNotAuthenticatedException ex) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "registered", false,
                        "message", ex.getMessage()
                ));
    }
}