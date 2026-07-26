package com.ecommerce.exception.registration;

public class BadRequestException extends RuntimeException{
    public BadRequestException (String message)
    {
        super(message);
    }
}
