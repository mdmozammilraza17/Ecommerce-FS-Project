package com.ecommerce.product_service.exception;

import com.ecommerce.product_service.exception.CategoryNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<Map<String, Object>> handleCategoryNotFound(
            CategoryNotFound ex) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CategoryServiceUnavailableException.class)
    public ResponseEntity<Map<String, Object>>
    handleCategoryServiceUnavailable(
            CategoryServiceUnavailableException ex) {

        Map<String, Object> response = new HashMap<>();

        response.put("status",
                HttpStatus.SERVICE_UNAVAILABLE.value());
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(
                response,
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }
}