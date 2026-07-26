package com.ecommerce.exception.registration;

public class ConflictException extends RuntimeException{
    public ConflictException (String message)
    {
        super(message);
    }
}
