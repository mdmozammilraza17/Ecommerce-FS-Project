package com.categories.service;

import com.categories.dto.CategoryRequestDTO;
import com.categories.dto.CategoryResponseDTO;
import com.categories.dto.CategoryTreeDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO createCategory(CategoryRequestDTO dto);

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO getCategoryById(Long id);

    List<CategoryTreeDTO> getCategoryTree();
}