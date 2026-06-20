package com.ecommerce.product_service.exception;

public class CategoryNotFound extends RuntimeException{
    public CategoryNotFound (String message)
    {
        super(message);
    }
}
