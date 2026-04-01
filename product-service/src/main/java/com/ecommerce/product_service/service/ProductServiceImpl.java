package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.Product;
import com.ecommerce.product_service.dto.ProductDTO;
import com.ecommerce.product_service.entity.ProductEntity;
import com.ecommerce.product_service.kafka.producer.ProductProducer;
import com.ecommerce.product_service.repository.ProductRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    final RestTemplate restTemplate;

    private final ProductProducer productProducer;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, RestTemplate restTemplate, ProductProducer productProducer) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
        this.productProducer = productProducer;
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

        // Prepare Avro Product
        BigDecimal price = productDTO.getPrice().setScale(2, RoundingMode.HALF_UP);
        ByteBuffer priceBuffer = ByteBuffer.wrap(price.unscaledValue().toByteArray());

        Product product = Product.newBuilder()
                .setProductId(savedProduct.getProductId())
                .setProductName(productDTO.getProductName())
                .setDescription(productDTO.getDescription())
                .setBrand(productDTO.getBrand())
                .setCategory(productDTO.getCategory())
                .setQuantity(productDTO.getQuantity())
                .setPrice(priceBuffer)
                .setImageUrl(productDTO.getImageUrl())
                .build();

        // Send to Kafka
        productProducer.sendProduct(product);

        // Return as Entity to DTO
        return modelMapper.map(savedProduct, ProductDTO.class);
    }
}
