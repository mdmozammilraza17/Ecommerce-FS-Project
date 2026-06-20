package com.ecommerce.product_service.exception;

public class CategoryServiceUnavailableException extends RuntimeException {
    public CategoryServiceUnavailableException(String message) {
        super(message);
    }
}
