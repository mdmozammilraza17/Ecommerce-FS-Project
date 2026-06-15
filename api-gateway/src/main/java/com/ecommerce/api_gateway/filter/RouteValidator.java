package com.ecommerce.api_gateway.filter;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private static final Set<String> OPEN_API_ENDPOINTS = Set.of(
            "/api/users/register",
            "/api/auth/token"
    );

    public final Predicate<ServerHttpRequest> isSecured = request -> {

        // Allow CORS preflight requests
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            return false;
        }

        String path = request.getURI().getPath();

        return !OPEN_API_ENDPOINTS.contains(path);
    };
}