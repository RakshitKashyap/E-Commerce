package com.example.Order_Service.Order.exceptions;

import com.example.Order_Service.Order.utility.Enum.CheckedException;

public class CustomException extends RuntimeException{
    private final CheckedException checkedExceptions;

    public CustomException(CheckedException exceptions) {
        super(exceptions.getMessage());
        this.checkedExceptions = exceptions;
    }

    public int getErrorCode(){
        return checkedExceptions.getErrorCode();
    }
}
