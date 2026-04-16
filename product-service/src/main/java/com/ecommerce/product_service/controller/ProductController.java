package com.ecommerce.product_service.controller;


import com.ecommerce.product_service.dto.ProductDTO;
import com.ecommerce.product_service.entity.ProductEntity;
import com.ecommerce.product_service.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/products")
public class ProductController {

    final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }


    @GetMapping ("/get")
    public String getProduct ()
    {
        return "This is called by Product Controller: "+productService.callUserService();
    }

    @PostMapping ("/create/product")
    public ResponseEntity<ProductDTO> createProduct (@RequestBody ProductDTO productDTO)
    {
        ProductDTO savedProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping ("/about")
    public String getAbout ()
    {
        return "This is unauthenticated because this is about";
    }
}
