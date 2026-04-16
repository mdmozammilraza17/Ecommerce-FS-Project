package com.ecommerce.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator (RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route("USER-SERVICE", r ->
                        r.path("/api/users/**")
                                .uri("lb://USER-SERVICE"))
                .route("PRODUCT-SERVICE", r ->
                        r.path("/api/products/**")
                                .uri("lb://PRODUCT-SERVICE"))
                .route("AUTH-SERVICE", r ->
                        r.path("/api/auth/**")
                                .uri("lb://AUTH-SERVICE"))
                .route("INVENTORY-SERVICE", r ->
                        r.path("/api/inventory/**")
                                .uri("lb://INVENTORY-SERVICE"))

                .build();
    }
}
