package com.ecommerce.product_service.service;
import com.ecommerce.product_service.controller.ProductController;
import com.ecommerce.product_service.dto.ProductDTO;
import com.ecommerce.product_service.entity.ProductEntity;
import com.ecommerce.product_service.mapper.ModelMapperConfig;
import com.ecommerce.product_service.repository.ProductRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    final RestTemplate restTemplate;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        
        // DTO to Entity
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        productEntity.setActive(true);

        // Save product
        ProductEntity savedProduct = productRepository.save(productEntity);
        
        // Return as Entity to DTO
        return modelMapper.map(savedProduct, ProductDTO.class);
    }
}
