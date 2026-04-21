package com.example.Order_Service.Order.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Getter
public class ErrorOutput {
    private int statusCode;
    private String message;
    private LocalDateTime timeStamp;
}
