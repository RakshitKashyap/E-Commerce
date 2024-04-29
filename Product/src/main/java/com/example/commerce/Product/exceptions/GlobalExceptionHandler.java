package com.example.commerce.Product.exceptions;

import com.example.commerce.Product.utils.enums.CheckedExceptions;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorOutput> handleCustomException(CheckedExceptions exception){
        ErrorOutput response = new ErrorOutput(exception.getErrorCode(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(exception.getErrorCode()));
    }
}
