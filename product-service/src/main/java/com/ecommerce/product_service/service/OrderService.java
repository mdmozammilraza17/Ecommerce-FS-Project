package com.ecommerce.product_service.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String callUserService ()
    {
        String provideUrl = "http://USER-SERVICE/get/user";
        return restTemplate.getForObject(provideUrl, String.class);
    }
}
