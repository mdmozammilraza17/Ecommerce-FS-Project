package com.ecommerce.product_service.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String callUserService ()
    {
        String provideUrl = "http://USER-SERVICE/api/users/get";
        return restTemplate.getForObject(provideUrl, String.class);
    }
}
