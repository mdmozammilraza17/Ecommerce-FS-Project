package com.ecommerce.product_service.controller;


import com.ecommerce.product_service.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/products")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping ("/get")
    public String getProduct ()
    {
        return "This is called by Product Controller: "+productService.callUserService();
    }
}
