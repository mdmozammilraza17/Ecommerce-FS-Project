package com.categories.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String name;

    private List<CategoryResponseDTO> children = new ArrayList<>();

    public CategoryResponseDTO() {
    }

    public CategoryResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}