package com.ecommerce.kafka.consumer;

import com.ecommerce.product_service.dto.Product;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener (topics = "product-topic", groupId = "notification-group")
    public void consumeNotification (Product product)
    {
        System.out.println("Your created a product with product id: "+product.getProductId());
    }
}
