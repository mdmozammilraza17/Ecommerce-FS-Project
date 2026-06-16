package com.ecommerce.product_service.service;

import com.ecommerce.product_service.config.CategoryClient;
import com.ecommerce.product_service.dto.*;
import com.ecommerce.product_service.entity.ProductEntity;
import com.ecommerce.product_service.kafka.producer.ProductProducer;
import com.ecommerce.product_service.mapper.ProductMapper;
import com.ecommerce.product_service.repository.ProductRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryClient categoryClient;

    private final ProductProducer productProducer;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryClient categoryClient, ProductProducer productProducer, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryClient = categoryClient;
        this.productProducer = productProducer;
        this.productMapper = productMapper;
    }


    public String fallbackUser(Exception ex) {
        return "User service is currently unavailable";
    }

    public String fallbackRateLimitMsg (Throwable ex)
    {
        return "Too many request, Pls try again later...";
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {

        // DTO to Entity
        ProductEntity productEntity = productMapper.toEntity(productRequestDTO);
        productEntity.setActive(true);

        // Save product
        ProductEntity savedProduct = productRepository.save(productEntity);

        // Prepare Avro Product
        BigDecimal price = savedProduct.getPrice().setScale(2, RoundingMode.HALF_UP);
        ByteBuffer priceBuffer = ByteBuffer.wrap(price.unscaledValue().toByteArray());

        Product product = Product.newBuilder()
                .setProductId(savedProduct.getProductId())
                .setProductName(savedProduct.getProductName())
                .setDescription(savedProduct.getDescription())
                .setBrand(savedProduct.getBrand())
                .setQuantity(savedProduct.getQuantity())
                .setCategoryId(savedProduct.getCategoryId())
                .setPrice(priceBuffer)
                .setSku(savedProduct.getSku())
                .build();

        // Send to Kafka
        productProducer.sendProduct(product);

        // Entity → ResponseDTO
        ProductResponseDTO responseDTO = productMapper.toDTO(savedProduct);

        return responseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductResponseDTO dto = productMapper.toDTO(product);

        // Call category
        CategoryResponseDTO category = categoryClient.getCategoryById(product.getCategoryId());

        dto.setCategoryResponseDTO(category);

        return dto;
    }

    @Override
    public PageResponseDTO<ProductResponseDTO> getAllProducts(
            int page, int size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProductEntity> productPage = productRepository.findAll(pageable);

        PageResponseDTO<ProductResponseDTO> response = new PageResponseDTO<>();

        response.setContent(
                productPage.getContent()
                        .stream().map(product ->
                        {
                            ProductResponseDTO dto = productMapper.toDTO(product);

                            CategoryResponseDTO category = categoryClient.getCategoryById(
                                    product.getCategoryId()
                            );
                            dto.setCategoryResponseDTO(category);
                            return dto;
                        }
        ).toList());
        response.setPage(productPage.getNumber());
        response.setSize(productPage.getSize());
        response.setTotalElements(productPage.getTotalElements());
        response.setTotalPages(productPage.getTotalPages());
        response.setFirst(productPage.isFirst());
        response.setLast(productPage.isLast());
        return response;
    }
}
