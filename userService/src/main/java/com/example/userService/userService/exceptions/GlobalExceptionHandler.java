package com.example.userService.userService.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomExceptions.class)
    public ResponseEntity<ErrorOutput> handleCustomException(CustomExceptions exception){
        ErrorOutput response = new ErrorOutput(exception.getErrorCode(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(exception.getErrorCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorOutput> handleAnyException(Exception ex) {
        ErrorOutput response = new ErrorOutput(500, ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.internalServerError().body(response);
    }
}
