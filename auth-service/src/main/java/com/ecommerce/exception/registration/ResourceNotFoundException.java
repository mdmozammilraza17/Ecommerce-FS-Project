package com.ecommerce.exception.registration;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException (String message)
    {
        super(message);
    }
}
