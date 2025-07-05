package com.example.commerce.Product.exceptions;

import com.example.commerce.Product.utils.enums.CheckedExceptions;

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
