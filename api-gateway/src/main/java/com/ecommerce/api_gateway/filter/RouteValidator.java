package com.ecommerce.api_gateway.filter;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private static final Set<String> OPEN_API_ENDPOINTS = Set.of(
            "/api/auth/signup",
            "/api/auth/login"
    );

    public final Predicate<ServerHttpRequest> isSecured = request -> {

        // CORS preflight
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            return false;
        }

        String path = request.getURI().getPath();

        // GET banner APIs are public
        if (HttpMethod.GET.equals(request.getMethod())
                && path.startsWith("/api/banner")) {
            return false;
        }

        // Public exact endpoints
        return !OPEN_API_ENDPOINTS.contains(path);
    };
}