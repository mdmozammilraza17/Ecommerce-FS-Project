package com.ecommerce.product_service.service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @CircuitBreaker(name = "userServices", fallbackMethod = "fallbackUser")
    @Retry(name = "userServices")
    @RateLimiter(name = "userServices", fallbackMethod = "fallbackRateLimitMsg")
    public String callUserService ()
    {
        String provideUrl = "http://USER-SERVICE/api/users/get";
        return restTemplate.getForObject(provideUrl, String.class);
    }

    public String fallbackUser(Exception ex) {
        return "User service is currently unavailable";
    }

    public String fallbackRateLimitMsg (Throwable ex)
    {
        return "Too many request, Pls try again later...";
    }
}
