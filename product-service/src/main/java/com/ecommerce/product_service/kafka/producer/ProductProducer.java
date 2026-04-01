package com.ecommerce.product_service.kafka.producer;


import com.ecommerce.product_service.dto.Product;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {

    private final KafkaTemplate<String, Product> kafkaTemplate;

    public ProductProducer(KafkaTemplate<String, Product> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProduct(Product product) {
        kafkaTemplate.send("product-topic", product);
        System.out.println("Sent to Kafka: " + product.getProductName());
    }
}
