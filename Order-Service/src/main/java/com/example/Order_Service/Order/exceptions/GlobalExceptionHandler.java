package com.example.Order_Service.Order.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorOutput> handleCustomException(CustomException exception){
        ErrorOutput response = new ErrorOutput(exception.getErrorCode(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(exception.getErrorCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorOutput> handleAnyException(Exception ex) {
        ErrorOutput response = new ErrorOutput(500, "An unexpected error occurred", LocalDateTime.now());
        return ResponseEntity.internalServerError().body(response);
    }
}
