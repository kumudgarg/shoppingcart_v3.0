package com.thoughtworks.shoppingcart.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullProductTypeException extends RuntimeException{

    private HttpStatus statusCode;

    public NullProductTypeException(String message,HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
