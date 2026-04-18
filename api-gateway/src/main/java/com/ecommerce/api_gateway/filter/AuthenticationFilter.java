package com.ecommerce.api_gateway.filter;

import com.ecommerce.api_gateway.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;

    private final JwtUtil jwtUtil;

    public AuthenticationFilter(RouteValidator validator, JwtUtil jwtUtil) {
        super(Config.class);
        this.validator = validator;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            if (validator.isSecured.test(exchange.getRequest())) {

                String authHeader = exchange.getRequest()
                        .getHeaders()
                        .getFirst("Authorization");

                // 1. header + bearer validation
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                String token = authHeader.substring(7);

                try
                {
//                    template.getForObject("http//AUTH-SERVICE/validate?token" + authHeader,String.class);
                    // Rest call to AUTH service
                    jwtUtil.validateToken(token);

                } catch (Exception e)
                {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}