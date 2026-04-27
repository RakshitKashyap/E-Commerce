package com.example.userService.userService.exceptions;

import com.example.userService.userService.utils.CheckedExceptions;

public class CustomExceptions  extends RuntimeException{
    private final CheckedExceptions checkedExceptions;
    public CustomExceptions(CheckedExceptions exceptions) {
        super(exceptions.getMessage());
        this.checkedExceptions = exceptions;
    }
    public int getErrorCode(){
        return checkedExceptions.getErrorCode();
    }
}
