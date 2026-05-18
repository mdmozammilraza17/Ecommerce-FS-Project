package com.categories.controller;

import com.categories.dto.CategoryRequestDTO;
import com.categories.dto.CategoryResponseDTO;
import com.categories.dto.CategoryTreeDTO;
import com.categories.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    // Create Category
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(
           @Valid @RequestBody CategoryRequestDTO dto) {

        CategoryResponseDTO response = service.createCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get All Categories (flat)
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    // Get Category by ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    // Get Category Tree
    @GetMapping("/tree")
    public ResponseEntity<List<CategoryTreeDTO>> getTree() {
        return ResponseEntity.ok(service.getCategoryTree());
    }
}