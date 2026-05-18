package com.categories.service;

import com.categories.dto.CategoryRequestDTO;
import com.categories.dto.CategoryResponseDTO;
import com.categories.dto.CategoryTreeDTO;
import com.categories.entity.Category;
import com.categories.mapper.CategoryMapper;
import com.categories.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper, CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    // Create Category
    @Transactional
    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {

        Category category = mapper.toEntity(dto);

        // Set Parent
        if (dto.getParentId() != null) {
            Category parent = repository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        }

        Category saved = repository.save(category);

        return mapper.toResponseDTO(saved);
    }

    // Get All Categories (flat)
    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> rootCategories =
                categoryRepository.findByParentIsNull();

        return rootCategories.stream()
                .map(categoryMapper::toResponseDTO)
                .toList();
    }

    // Get by ID
    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return mapper.toResponseDTO(category);
    }

    // Get Full Category Tree
    @Override
    public List<CategoryTreeDTO> getCategoryTree() {

        List<Category> allCategories = repository.findAll();

        Map<Long, List<Category>> childrenMap = allCategories.stream()
                .filter(c -> c.getParent() != null)
                .collect(Collectors.groupingBy(c -> c.getParent().getId()));

        return allCategories.stream()
                .filter(c -> c.getParent() == null)
                .map(c -> buildTree(c, childrenMap))
                .toList();
    }

    // Recursive Tree Builder
    private CategoryTreeDTO buildTree(Category category,
                                      Map<Long, List<Category>> childrenMap) {

        CategoryTreeDTO dto = new CategoryTreeDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        List<CategoryTreeDTO> children = childrenMap
                .getOrDefault(category.getId(), List.of())
                .stream()
                .map(child -> buildTree(child, childrenMap))
                .toList();

        dto.setChildren(children);

        return dto;
    }
}