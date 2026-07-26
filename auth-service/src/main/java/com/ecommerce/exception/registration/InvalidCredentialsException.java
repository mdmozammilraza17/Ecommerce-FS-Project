package com.ecommerce.exception.registration;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(String message)
    {
        super(message);
    }
}
